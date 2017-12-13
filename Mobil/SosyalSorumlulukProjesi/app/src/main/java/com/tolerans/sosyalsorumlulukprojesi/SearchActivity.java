package com.tolerans.sosyalsorumlulukprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    String url= "http://service.sosyalsorumluluk.mansetler.org/urunler/filtreleme?";
    Spinner spKategori,spMemleket;
    EditText edtAd;
    Button btnAra;
    CheckBox chkKategori,chkMemleket,chkAD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

       spKategori = (Spinner) findViewById(R.id.spAramaKategori);
        spMemleket = (Spinner) findViewById(R.id.spAramaKonum);
       edtAd = (EditText) findViewById(R.id.edtArama);
        btnAra = (Button) findViewById(R.id.btnAra);
         chkMemleket = (CheckBox) findViewById(R.id.chkMmeleket);
        chkKategori = (CheckBox) findViewById(R.id.chkKategori);
        chkAD = (CheckBox) findViewById(R.id.chkAd);
        final ArrayAdapter<String> memleketAdapter;
        final ArrayAdapter<String> kategoriAdapter;
        final ArrayList<String> memleketler = new ArrayList<>();
        final ArrayList<String> kategoriler = new ArrayList<>();
        final LinearLayout lyAd = (LinearLayout) findViewById(R.id.lnAd);
        final LinearLayout lyMemleket = (LinearLayout) findViewById(R.id.lnKonum);
        final LinearLayout lyKategori = (LinearLayout) findViewById(R.id.lnKategori);



        memleketAdapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_spinner_item, memleketler);
        memleketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMemleket.setAdapter(memleketAdapter);

        kategoriAdapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_spinner_item, kategoriler);
        kategoriAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKategori.setAdapter(kategoriAdapter);


        StringRequest requestKategori = new StringRequest(Request.Method.GET,"http://service.sosyalsorumluluk.mansetler.org/kategori/listele", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray jsonArray = new JSONObject(response).getJSONArray("kategoriler");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        kategoriler.add(jsonObject.getString("kategori_adi"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                kategoriAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"kategorileri çekerken hata oldu",Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(requestKategori);
        StringRequest requestMemleket = new StringRequest(Request.Method.GET,"http://service.sosyalsorumluluk.mansetler.org/memleket/listele", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONObject(response).getJSONArray("memleketler");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        memleketler.add(jsonObject.getString("memleket_adi"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                memleketAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"memleketleri çekerken hata oldu",Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(requestMemleket);

        btnAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chkMemleket.isChecked()){
                    url += "urun_konumu="+ (spMemleket.getSelectedItemPosition()+1)+"&";
                }
                if(chkAD.isChecked()){
                    url += "urun_adi="+edtAd.getText().toString()+"&";
                }
                if(chkKategori.isChecked()){
                    url += "kategori_id="+(spKategori.getSelectedItemPosition()+1)+"&";
                }


                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(SearchActivity.this,MainActivity.class);
                        intent.putExtra("filtreJson",response);
                        Toast.makeText(getApplication(),response.toString(),Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplication(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
                    Volley.newRequestQueue(getApplicationContext()).add(request);

            }
        });


        chkAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAD.isChecked()){
                    lyAd.setVisibility(View.VISIBLE);
                }
                else{
                    lyAd.setVisibility(View.GONE);
                }
            }
        });
        chkKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkKategori.isChecked()){
                    lyKategori.setVisibility(View.VISIBLE);
                }
                else{
                    lyKategori.setVisibility(View.GONE);
                }
            }
        });
        chkMemleket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkMemleket.isChecked()){
                    lyMemleket.setVisibility(View.VISIBLE);
                }
                else{
                    lyMemleket.setVisibility(View.GONE);
                }
            }
        });
    }
}
