package com.tolerans.sosyalsorumlulukprojesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class BaskaProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baska_profil);


        String json = getIntent().getExtras().getString("kullaniciJson");
        String resimUrl = "";
        TextView ad,mail,memleket,dTarihi,tel;
        ImageView resim;

        ad= (TextView) findViewById(R.id.digertxtProfilAd);
        mail = (TextView) findViewById(R.id.digertxtProfilMail);
        resim = (ImageView) findViewById(R.id.digerimgProfilResim);
        memleket = (TextView) findViewById(R.id.digertxtProfilMemleket);
        dTarihi = (TextView) findViewById(R.id.digertxtProfildTarihi);
        tel = (TextView) findViewById(R.id.digertxtProfilTel);

        if(!json.equals("")){

            try {
                JSONObject jsonObject = new JSONObject(json);
                ad.setText(jsonObject.getJSONObject("kullanici").getString("adi_soyadi"));
                dTarihi.setText(jsonObject.getJSONObject("kullanici").getString("dogum_tarihi"));
                memleket.setText(jsonObject.getJSONObject("kullanici").getString("memleket_id"));
                mail.setText(jsonObject.getJSONObject("kullanici").getString("mail"));
                tel.setText(jsonObject.getJSONObject("kullanici").getString("telefon"));
                resimUrl =  jsonObject.getJSONObject("kullanici").getString("resim");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        if(!resimUrl.equals("")){
            String resimUrlDuzenle = resimUrl.replace("\\","");
            Picasso.with(getApplicationContext()).load(resimUrlDuzenle).into(resim);
        }


    }
}
