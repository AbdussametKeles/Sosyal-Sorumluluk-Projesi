package com.tolerans.sosyalsorumlulukprojesi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.MailTo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button btnKaydol,btnGiris;
    EditText edtMail,edtSifre;
    ProgressDialog pDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        edtMail= (EditText) findViewById(R.id.txtMail);
        edtSifre= (EditText) findViewById(R.id.txtSifre);
        btnKaydol = (Button) findViewById(R.id.btnKaydolYonlendir);
        btnGiris = (Button) findViewById(R.id.btnKGiris);


        //SharedPreference içindeki verileri çekiyoruz.

       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String sharedMail = preferences.getString("userMail","bos");
        String sharedSifre = preferences.getString("userPassword","bos");

        //burada otomatik olarak kullanıcının login olması gerekiyor.Login işlevini bir metod ile sağlayacağız.

        if(!sharedMail.equals("bos")&&!sharedSifre.equals("bos")){
            GirisYap(sharedMail,sharedSifre);
        }




        btnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,KayitActivity.class);
                startActivity(intent);
            }
        });
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GirisYap(edtMail.getText().toString(),edtSifre.getText().toString());
            }
        });


    }
    public void GirisYap(final String email, final String sifre){
        String token ;
        pDialog.setMessage("Giriş Yapılıyor..");
        pDialog.show();
        String URL = "http://service.sosyalsorumluluk.mansetler.org/kullanici/giris?mail="+email+"&sifre="+sifre;
        StringRequest request = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            sharedPreferenceKaydet(email,sifre);
                            Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();


                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String sharedMail = preferences.getString("userMail","bos");
        String sharedSifre = preferences.getString("userPassword","bos");

       //Eğer shared preferencesin içi yukarıda doldurulmuşsa ya da kayıt bölümünde doldurulmuşssa yani
        //responsede bir hata yoksa Main activityye yönlendirecek.

        if(!sharedMail.equals("bos")&&!sharedSifre.equals("bos")){
           Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("mail",email);
            startActivity(intent);
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


