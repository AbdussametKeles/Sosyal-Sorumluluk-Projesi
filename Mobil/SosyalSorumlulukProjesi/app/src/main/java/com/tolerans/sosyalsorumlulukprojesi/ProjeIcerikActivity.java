package com.tolerans.sosyalsorumlulukprojesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ProjeIcerikActivity extends AppCompatActivity {
    TextView txtBaslik, txtYazar, txtKonum,txtTarih,txtIcerik;
    RecyclerView rcResimler,rcYorumlar;
    Button btnYorum;
    EditText edtYorum;
    Projeler proje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proje_icerik);



        if(getIntent().getSerializableExtra("proje")!=null){
            //sunları sunları yap
            proje = (Projeler) getIntent().getSerializableExtra("proje");
        }


        txtBaslik = (TextView) findViewById(R.id.txtProjeBaslik);
        txtIcerik = (TextView) findViewById(R.id.txtProjeIcerik);
        txtKonum = (TextView) findViewById(R.id.txtProjeKonum);
        txtTarih = (TextView) findViewById(R.id.txtProjeTarih);
        txtYazar = (TextView) findViewById(R.id.txtProjeYazar);
        btnYorum = (Button) findViewById(R.id.btnprojeYorum);
        rcResimler = (RecyclerView) findViewById(R.id.projeResimler);
        rcYorumlar = (RecyclerView) findViewById(R.id.projeYorumlar);
        if(proje.getResimler() != null && proje.getResimler().size() != 0 && !proje.getResimler().contains(null) && !proje.getResimler().contains(""))
        {
            ResimAdapter resimAdapter = new ResimAdapter(proje.getResimler(),getApplicationContext());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
            rcResimler.setLayoutManager(layoutManager);
            rcResimler.setAdapter(resimAdapter);
        }

        txtBaslik.setText(proje.getBaslik());
        txtKonum.setText(String.valueOf(proje.getKonumID()));
        txtYazar.setText(String.valueOf(proje.yazarId));
        txtIcerik.setText(proje.getIcerik());

        YorumAdapter yorumAdapter = new YorumAdapter(proje.getYorumlar(),getApplicationContext());

        LinearLayoutManager yorumManager = new LinearLayoutManager(getApplicationContext(),1,false);
        rcYorumlar.setAdapter(yorumAdapter);
        rcYorumlar.setLayoutManager(yorumManager);


    }
}
