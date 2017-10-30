package com.tolerans.sosyalsorumlulukprojesi;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class KayitActivity extends AppCompatActivity {

    private static final String REGISTER_URL ="http://service.sosyalsorumluluk.mansetler.org/kullanici/kayit";
    private Button btnKayit;
    private EditText edtAdSoyad,edtSifre,edtMail,edtTelefon,edtResim;
    private Spinner spMemleket;
    private TextView edtDogumTarihi;
    private ProgressDialog pDialog;
    private DatePickerDialog.OnDateSetListener dataSetListener;
    private String dogumTarihi,memleket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        btnKayit = (Button) findViewById(R.id.btnKayit);
        edtAdSoyad = (EditText) findViewById(R.id.edtAdSoyad);
        edtMail = (EditText) findViewById(R.id.edtMail);
        spMemleket = (Spinner) findViewById(R.id.spMemleket);
        edtSifre = (EditText) findViewById(R.id.edtSifre);
        edtTelefon = (EditText) findViewById(R.id.edtTelefon);
        edtDogumTarihi = (TextView) findViewById(R.id.edtDogumTarihi);
        edtResim = (EditText) findViewById(R.id.edtResim);

        //textviewin onclick methodu
        edtDogumTarihi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calendar dan bir örnek çağırdık
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                //İçerisinde bir datepicker olan diyalog penceresi
                DatePickerDialog dialog = new DatePickerDialog(
                        KayitActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dataSetListener,
                        year, month, day);
                //arkaplanı transparent yaptık
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        spMemleket.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                memleket= String.valueOf(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                memleket = String.valueOf(1);
            }
        });

        //datepicker içerinden tarih seçildiğinde çalışacak
        dataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month +=1;
                dogumTarihi = year+"/"+month+"/"+dayOfMonth;
                edtDogumTarihi.setText(dogumTarihi);
            }
        };


        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adSoyad = edtAdSoyad.getText().toString();
                String mail = edtMail.getText().toString();
                String sifre = edtSifre.getText().toString();
                String telefon = edtTelefon.getText().toString();
                String resim = edtResim.getText().toString();

                //kayıt için uygun şartların kontrolünün burdan sağlayabiliriz.
                //şuan için tek şart boş olmamaları olduğu için null olup olmadığı kontrol edilecek
                if(!adSoyad.isEmpty() && !mail.isEmpty() && !dogumTarihi.isEmpty() && !memleket.isEmpty() && !sifre.isEmpty() && !telefon.isEmpty() &&
                        isValidEmail(mail) && sifre.length()>7 && sifre.length() <13){
                    kullaniciKayit(adSoyad,sifre,mail,telefon,dogumTarihi);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Lütfen düzgün biçimde doldurun",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void kullaniciKayit(final String adSoyad, final String sifre, final String mail, final String telefon, final String dogumTarihi) {

        String tag_string_req = "request_register";

        pDialog.setMessage("Kayıt Gerçekleştiriliyor ...");
        pDialog.show();

        //volley kütüphanesinin sağladığı bir nesne
        //kullanımı şu şekilde stringrequest(method,url,response.listener,response.errorlistener)
        StringRequest istek = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();

                        try {
                            //otomatik giriş yapmasını istemiyorsanız bu alanı kaldırın.
                            sharedPreferenceKaydet(mail,sifre);

                            JSONObject object = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),"Kaydınız başarıyla gerçekleştirildi",Toast.LENGTH_LONG).show();

                            //kullanıcıyı giriş için login sayfasına yönlendiriyoruz.
                            Intent intent = new Intent(KayitActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();

                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("adi_soyadi",adSoyad);
                params.put("dogum_tarihi",dogumTarihi);
                params.put("memleket_id",memleket);
                params.put("sifre",sifre);
                params.put("mail",mail);
                params.put("telefon",telefon);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(istek);


    }


    public final static boolean isValidEmail(CharSequence mail) {
        //textboxın mail şeklinde olup olmadığını kontrol ediyor
        if (mail == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches();
        }
    }
    private void sharedPreferenceKaydet(String mail, String sifre) {
        //SharedPreferences uygulamayı kapatıp açtığımızda dahi uygulamada oturumun devam etmesini sağlıyor
        //Bu methodda ona veri değişkenleri atıyoruz.
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preference.edit();

        editor.putString("userPassword",sifre);
        editor.putString("userMail",mail);
        editor.commit();
    }



}
