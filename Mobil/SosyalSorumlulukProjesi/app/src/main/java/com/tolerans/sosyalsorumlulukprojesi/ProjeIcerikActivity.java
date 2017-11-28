package com.tolerans.sosyalsorumlulukprojesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProjeIcerikActivity extends AppCompatActivity {
    TextView txtBaslik, txtYazar, txtKonum,txtTarih,txtIcerik;
    ImageView imgProje;
    Button btnYorum;
    EditText edtYorum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proje_icerik);


        txtBaslik = (TextView) findViewById(R.id.txtProjeBaslik);
        txtIcerik = (TextView) findViewById(R.id.txtProjeIcerik);
        txtKonum = (TextView) findViewById(R.id.txtProjeKonum);
        txtTarih = (TextView) findViewById(R.id.txtProjeTarih);
        txtYazar = (TextView) findViewById(R.id.txtProjeYazar);
        imgProje = (ImageView) findViewById(R.id.ImgProjeFoto);
        btnYorum = (Button) findViewById(R.id.btnprojeYorum);


    }
}
