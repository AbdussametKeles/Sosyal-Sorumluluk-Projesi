package com.tolerans.sosyalsorumlulukprojesi;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ProjeEkleActivity extends AppCompatActivity {
    ArrayList<String> memleketList;
    ArrayList<String> kategoriList;
    JSONArray jsonArrayKategori,jsonArrayMemleket;
    Spinner spMemleket,spKategori;
    int memleketID,kategoriID;
    EditText edtBaslik,edtIcerik,edtBagisT;
    ImageView imgProjeFoto;
    Button btnEkle,btnResimSec;
    private Bitmap bitmap;
    private Uri filePath;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private int PICK_IMAGE_REQUEST = 1;
    String json,token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proje_ekle);


        requestStoragePermission();

        btnEkle = (Button) findViewById(R.id.btnProjeEkle);
        edtBaslik = (EditText) findViewById(R.id.edtProjeBaslik);
        edtIcerik = (EditText) findViewById(R.id.edtProjeIcerik);
        edtBagisT = (EditText) findViewById(R.id.edtBagisTipi);
        btnResimSec = (Button) findViewById(R.id.btnProjeResim);
        imgProjeFoto = (ImageView) findViewById(R.id.ImgProjeFoto);


        spMemleket = (Spinner) findViewById(R.id.spEkleSehir);
        spKategori = (Spinner) findViewById(R.id.spEkleKategori);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){

            json = bundle.getString("jsonveriler");
            try {
                JSONObject object = new JSONObject(json);
                token = object.getString("token");

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        memleketList = new ArrayList<String>();
        StringRequest request = new StringRequest(Request.Method.GET, "http://service.sosyalsorumluluk.mansetler.org/memleket/listele", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject memleketJson  = new JSONObject(response);//gelen json dosyası
                    jsonArrayMemleket = memleketJson.getJSONArray("memleketler");//genel json dosyasındanki verilerin array halin
                    for(int i=0;i<jsonArrayMemleket.length();i++){
                        JSONObject siradakiMemleket = jsonArrayMemleket.getJSONObject(i);//her json verisi için ayrı obje oluşturuyoruz ve bunu çekiyoruz.
                        memleketList.add(siradakiMemleket.getString("memleket_adi"));//listeye ekliyoruz birazdan spinnerin kaynağı olarak belirteceğiz.
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Memleketleri görüntülemede hata oluştu",Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(request);

        kategoriList = new ArrayList<String>();
        StringRequest kategoriRequest = new StringRequest(Request.Method.GET, "http://service.sosyalsorumluluk.mansetler.org/kategori/listele", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject kategoriJson  = new JSONObject(response);//gelen json dosyası
                    jsonArrayKategori = kategoriJson.getJSONArray("kategoriler");//genel json dosyasındanki verilerin array halin
                    for(int i=0;i<jsonArrayKategori.length();i++){
                        JSONObject siradakiKategori = jsonArrayKategori.getJSONObject(i);//her json verisi için ayrı obje oluşturuyoruz ve bunu çekiyoruz.
                        kategoriList.add(siradakiKategori.getString("kategori_adi"));//listeye ekliyoruz birazdan spinnerin kaynağı olarak belirteceğiz.
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Kategori görüntülemede hata oluştu",Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(kategoriRequest);



        spMemleket.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,memleketList));//spinnera kaynağjnı atadık
        spKategori.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,kategoriList));




        spMemleket.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //spinnerda bir değer seçildiğinde çalışacak method
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //seçilmiş olan değeri memleket idye atıyoruz ki ekleme işleminde kolaylık sağlasın
                try {
                    Toast.makeText(getApplicationContext(),jsonArrayMemleket.get(position).toString(),Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                memleketID = position+1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //hiçbirşey seçilmediğinde default olarak adana seçiyoruz.

                memleketID =1;
            }
        });

        spKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kategoriID=position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                kategoriID=1;
            }
        });

        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projeEkle();
            }
        });
        btnResimSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });


    }

    private void projeEkle() {
        Toast.makeText(getApplicationContext(),String.valueOf(memleketID)+ " "+ String.valueOf(kategoriID),Toast.LENGTH_LONG).show();

        try {
            new MultipartUploadRequest(this,"http://service.sosyalsorumluluk.mansetler.org/urunler/yayinlama")
                    .addFileToUpload(getPath(filePath),"resim")
                    .addParameter("token_string","59f508bf36462")
                    .addParameter("kategori_id", String.valueOf(2))
                    .addParameter("urun_konumu", String.valueOf(20))
                    .addParameter("bagis_tipi",edtBagisT.getText().toString())
                    .addParameter("urun_adi",edtBaslik.getText().toString())
                    .addParameter("urun_aciklamasi",edtIcerik.getText().toString())
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void requestStoragePermission() {
        //depolama izni methodu
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //request kod eşleşiyorsa
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //izin kontrolü
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Depo Alanı artık okunabilir", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "İzin esnasında hata oluştu", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void showFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Resim Seç"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imgProjeFoto.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getPath(Uri uri) {

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }


}
