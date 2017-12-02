package com.tolerans.sosyalsorumlulukprojesi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Random;


public class LoginActivity extends AppCompatActivity {
    Button btnKaydol,btnGiris,btnSifremiUnuttum;
    EditText edtMail,edtSifre,edtCevap;
    TextView sayiTxt;
    ProgressDialog pDialog ;
    LinearLayout linearLayout;
    CheckBox checkBox;
   int sayi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nesneleriTanimla();//findview Tanımlamaları

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Random random = new Random();//recapctha kısmı için random sayı ayarlıyor.
        sayi = random.nextInt(10)+1;
        sayiTxt.setText(String.valueOf(sayi));



        //SharedPreference içindeki verileri çekiyoruz.
       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String sharedMail = preferences.getString("userMail","bos");
        String sharedSifre = preferences.getString("userPassword","bos");


        if(!sharedMail.equals("bos")&&!sharedSifre.equals("bos")){
            //önceden sharedpreference doluysa otomatik giriş yap.
            GirisYap(sharedMail,sharedSifre);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBox.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

        btnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,KayitActivity.class);
                startActivity(intent);
            }
        });
        btnSifremiUnuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SifreYenilemeActivity.class);
                startActivity(intent);
            }
        });
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int karesi = Integer.valueOf(edtCevap.getText().toString());
                int cevap = sayi*sayi;

                if(edtMail.getText().equals("")&&edtSifre.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"Lütfen alanları boş bırakmayın",Toast.LENGTH_LONG).show();
                }
                else{
                    if(karesi != cevap){
                        Toast.makeText(getApplicationContext(),"Karesini yanlış girdiniz",Toast.LENGTH_LONG).show();
                    }
                    else{
                        GirisYap(edtMail.getText().toString(),edtSifre.getText().toString());
                    }

                }
            }
        });


    }

    private void nesneleriTanimla() {
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        edtMail= (EditText) findViewById(R.id.txtMail);
        edtSifre= (EditText) findViewById(R.id.txtSifre);
        edtCevap = (EditText) findViewById(R.id.txtCevap);
        btnKaydol = (Button) findViewById(R.id.btnKaydolYonlendir);
        btnGiris = (Button) findViewById(R.id.btnKGiris);
        sayiTxt = (TextView) findViewById(R.id.sayi);
        checkBox = (CheckBox) findViewById(R.id.dogrulama);
        btnSifremiUnuttum = (Button) findViewById(R.id.btnSifremiUnuttum);
    }

    public void GirisYap(final String email, final String sifre){
        String token ;
        pDialog.setMessage("Giriş Yapılıyor..");
        pDialog.show();
        String URL = "http://service.sosyalsorumluluk.mansetler.org/kullanici/giris?mail="+email+"&sifre="+sifre;
        final StringRequest request = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pDialog.dismiss();
                        sharedPreferenceKaydet(email,sifre);
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("jsonveri",response.toString());//main activity ye json dosyasını yolluyorum.
                        startActivity(intent);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                if(error.toString().equals("com.android.volley.ServerError")){
                    Toast.makeText(getApplicationContext(),"Kullanıcı adı veya şifre yanlış",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
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


