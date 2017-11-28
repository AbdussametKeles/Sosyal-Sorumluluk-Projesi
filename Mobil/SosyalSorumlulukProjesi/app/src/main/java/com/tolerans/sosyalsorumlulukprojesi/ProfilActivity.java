package com.tolerans.sosyalsorumlulukprojesi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProfilActivity extends AppCompatActivity {
    String json;
    ImageView imgProfil;
    EditText editName,editTel,editMail,editSifre;
    Spinner editMemleket;
    String token;
    int position,memleketPosition;
    Button btnGuncelle;
    TextView profilTel ,profilDTarihi,profilSehir,profilAd,profilMail,txtdTarihi,profilSifre;
    private DatePickerDialog.OnDateSetListener dataSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        String [] memleketler = getResources().getStringArray(R.array.sehirler);



        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            Toast.makeText(getApplicationContext(),"Profilinizi görüntüleyemezsiniz",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ProfilActivity.this,MainActivity.class);
            startActivity(intent);
        }else{
            json =bundle.getString("jsonveriler");
        }
        btnGuncelle = (Button) findViewById(R.id.profilGuncelleBtn);
         profilAd = (TextView) findViewById(R.id.txtProfilAd);
         profilSehir = (TextView) findViewById(R.id.txtProfilMemleket);
         profilDTarihi = (TextView) findViewById(R.id.txtProfildTarihi);
         profilMail = (TextView) findViewById(R.id.txtProfilMail);
         profilTel = (TextView) findViewById(R.id.txtProfilTel);
        imgProfil = (ImageView) findViewById(R.id.imgProfilResim);
        editName = (EditText) findViewById(R.id.editNameTxt);
        editTel = (EditText) findViewById(R.id.editTelTxt);
        txtdTarihi = (TextView) findViewById(R.id.editdTarihiProfil);
        editMemleket = (Spinner) findViewById(R.id.editMemleketSpinner);
        editMail = (EditText) findViewById(R.id.editMailTxt);
        profilSifre = (TextView) findViewById(R.id.txtSifre);
        editSifre = (EditText) findViewById(R.id.editSifreTxt);


        try {
            JSONObject jsonObject = new JSONObject(json);
            token = jsonObject.getString("token");
            position = jsonObject.getJSONObject("kullan\u0131c\u0131").getInt("memleket_id")-1;
            profilMail.setText(jsonObject.getJSONObject("kullan\u0131c\u0131").getString("mail"));
            profilSifre.setText(jsonObject.getJSONObject("kullan\u0131c\u0131").getString("sifre"));
            profilAd.setText( jsonObject.getJSONObject("kullan\u0131c\u0131").getString("adi_soyadi").toString());

            profilSehir.setText(String.valueOf(memleketler[ jsonObject.getJSONObject("kullan\u0131c\u0131").getInt("memleket_id")-1]));
            profilDTarihi.setText(jsonObject.getJSONObject("kullan\u0131c\u0131").getString("dogum_tarihi").toString());
            profilDTarihi.setText(profilDTarihi.getText().toString().replace("-","/"));
            profilTel.setText(jsonObject.getJSONObject("kullan\u0131c\u0131").getString("telefon").toString());
            String resimUrl =jsonObject.getJSONObject("kullan\u0131c\u0131").getString("resim").toString();
            Toast.makeText(getApplicationContext(),resimUrl,Toast.LENGTH_LONG).show();
            Picasso.with(this).load(resimUrl).into(imgProfil);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtdTarihi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog= new DatePickerDialog(ProfilActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        dataSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                txtdTarihi.setText(year+"/"+month+"/"+dayOfMonth);
            }
        };

        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilGuncelle();
            }
        });


    }


    private void profilGuncelle() {

        StringRequest istek = new StringRequest(Request.Method.POST, "http://service.sosyalsorumluluk.mansetler.org/kullanici/profil_guncelle",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject object = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),"Güncelleştirme Gerçekleştirildi",Toast.LENGTH_LONG).show();
                            json = response;

                            Intent intent = new Intent(ProfilActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams(){

                Map<String,String> params = new HashMap<String,String>();
                params.put("token_string",token);
                params.put("adi_soyadi", editName.getText().toString());
                params.put("dogum_tarihi",txtdTarihi.getText().toString());
                params.put("memleket_id",String.valueOf(editMemleket.getSelectedItemId()+1));
                params.put("sifre",editSifre.getText().toString());
                params.put("telefon", editTel.getText().toString());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(istek);




        /*try {
            new MultipartUploadRequest(this, "http://service.sosyalsorumluluk.mansetler.org/kullanici/profil_guncelle")
                    .addParameter("token",token)
                    .addParameter("adi_soyadi", editName.getText().toString())
                    .addParameter("dogum_tarihi", txtdTarihi.getText().toString())
                    .addParameter("memleket_id", String.valueOf(editMemleket.getSelectedItemPosition()))
                    .addParameter("mail", editMail.getText().toString())
                    .addParameter("telefon", editTel.getText().toString())
                    .addParameter("sifre", editSifre.getText().toString())
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.action_profil).setVisible(false);
        menu.findItem(R.id.profil_edit).setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if(id == R.id.profil_edit) {
            profilTel.setVisibility(View.GONE);
            profilDTarihi.setVisibility(View.GONE);
            profilSehir.setVisibility(View.GONE);
            profilAd.setVisibility(View.GONE);
            profilSehir.setVisibility(View.GONE);
            editName.setText(profilAd.getText());
            editTel.setText(profilTel.getText());
            profilSifre.setVisibility(View.GONE);
            editSifre.setText(profilSifre.getText());
            editSifre.setVisibility(View.VISIBLE);
            profilMail.setVisibility(View.GONE);
            editMail.setVisibility(View.VISIBLE);
            editMail.setText(profilMail.getText());
            txtdTarihi.setText(profilDTarihi.getText());
            editMemleket.setSelection(position);
            editName.setVisibility(View.VISIBLE);
            editTel.setVisibility(View.VISIBLE);
           txtdTarihi.setVisibility(View.VISIBLE);
            editMemleket.setVisibility(View.VISIBLE);
            btnGuncelle.setVisibility(View.VISIBLE);

        }
        return super.onOptionsItemSelected(item);
    }
}
