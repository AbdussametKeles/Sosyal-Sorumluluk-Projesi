package com.tolerans.sosyalsorumlulukprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ProjeIcerikActivity extends AppCompatActivity {
    TextView txtBaslik, txtYazar, txtKonum,txtTarih,txtIcerik;
    EditText edtBaslik,edtYazar,edtKonum,edtIcerik;
    RecyclerView rcResimler,rcYorumlar;
    Button btnYorum,btnSil,btnKaydet;
    EditText edtYorum;
    Projeler proje;
    String token;
    int kullaniciID;
    LinearLayout ln;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proje_icerik);
   //     Toast.makeText(getApplicationContext(),getIntent().getExtras().getString("json"),Toast.LENGTH_LONG).show();
        ln = (LinearLayout) findViewById(R.id.lnProjeIcerik);

        if(getIntent().getExtras().getString("json")!=null){

            try {
                JSONObject jsonObject = new JSONObject(getIntent().getExtras().getString("json"));
                token = jsonObject.getString("token");
                kullaniciID = new JSONObject(getIntent().getExtras().getString("json")).getJSONObject("kullan\u0131c\u0131").getInt("kullanici_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ln.setVisibility(View.VISIBLE);
        }

        if(getIntent().getSerializableExtra("proje")!=null){
            //sunları sunları yap
            proje = (Projeler) getIntent().getSerializableExtra("proje");

        }



        btnKaydet = (Button) findViewById(R.id.projeDuzenleme);
        edtBaslik = (EditText) findViewById(R.id.edtProjeBaslik);
        edtIcerik = (EditText) findViewById(R.id.edtProjeIcerik);
        edtKonum = (EditText) findViewById(R.id.edtProjeKonum);
        edtYazar = (EditText) findViewById(R.id.edtProjeYazar);


        txtBaslik = (TextView) findViewById(R.id.txtProjeBaslik);
        txtIcerik = (TextView) findViewById(R.id.txtProjeIcerik);
        txtKonum = (TextView) findViewById(R.id.txtProjeKonum);
        txtTarih = (TextView) findViewById(R.id.txtProjeTarih);
        txtYazar = (TextView) findViewById(R.id.txtProjeYazar);
        btnYorum = (Button) findViewById(R.id.btnprojeYorum);
        btnSil = (Button) findViewById(R.id.projeSil);
        rcResimler = (RecyclerView) findViewById(R.id.projeResimler);
        rcYorumlar = (RecyclerView) findViewById(R.id.projeYorumlar);
        edtYorum = (EditText) findViewById(R.id.edtProjeYorumIcerik);
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


        if(proje.getYorumlar() != null && proje.getYorumlar().size() != 0 && !proje.getYorumlar().contains(null) && !proje.getYorumlar().contains(""))
        {
            LinearLayoutManager yorumManager = new LinearLayoutManager(getApplicationContext(),1,false);

            rcYorumlar.setLayoutManager(yorumManager);

            YorumAdapter yorumAdapter = new YorumAdapter(proje.getYorumlar(),getApplicationContext(),kullaniciID,proje.getYorumID(),proje.kullaniciIDs,token);
            rcYorumlar.setAdapter(yorumAdapter);

        }

        if(kullaniciID==proje.getYazarId()){
            btnKaydet.setVisibility(View.VISIBLE);
            btnSil.setVisibility(View.VISIBLE);
        }

        txtYazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://service.sosyalsorumluluk.mansetler.org/kullanici/goruntule?kullanici_id="+proje.getYazarId(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(ProjeIcerikActivity.this,BaskaProfilActivity.class);
                        Toast.makeText(getApplication(),response.toString(),Toast.LENGTH_LONG).show();
                        intent.putExtra("kullaniciJson",response);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
                Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
            }
        });

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://service.sosyalsorumluluk.mansetler.org/urunler/guncelle", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplication(),response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplication(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("token_string",token);
                        params.put("urun_id",String.valueOf(proje.getUrunID()));
                        params.put("kategori_id",String.valueOf(proje.getKategoriId()));
                        params.put("urun_adi",edtBaslik.getText().toString());
                        params.put("urun_konum",String.valueOf(edtKonum.getText()));
                        params.put("urun_aciklamasi",edtIcerik.getText().toString());
                        return params;
                    }
                };
            }
        });


        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   Toast.makeText(getApplicationContext(),token+" "+String.valueOf(proje.getUrunID()),Toast.LENGTH_LONG).show();
                // proje silme kodu daha öncesinde yazılmışım fakat github'a atmayı unutmuşum.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://service.sosyalsorumluluk.mansetler.org/urunler/silme", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<>();
                        params.put("token_string",token);
                        params.put("urun_id",String.valueOf(proje.getUrunID()));
                        return params;

                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
            }
        });
        btnYorum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://service.sosyalsorumluluk.mansetler.org/urunler/yorumlama", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<>();
                        params.put("token_string",token);
                        params.put("yorum_icerigi",edtYorum.getText().toString());
                        params.put("urun_id",String.valueOf(proje.getUrunID()));
                        return params;

                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        menu.findItem(R.id.action_profil).setVisible(false);
        if(kullaniciID==proje.getYazarId())
        {
            menu.findItem(R.id.profil_edit).setVisible(true);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.profil_edit){
            txtIcerik.setVisibility(View.GONE);
            txtYazar.setVisibility(View.GONE);
            txtKonum.setVisibility(View.GONE);
            txtBaslik.setVisibility(View.GONE);
            edtYazar.setVisibility(View.VISIBLE);
            edtBaslik.setVisibility(View.VISIBLE);
            edtKonum.setVisibility(View.VISIBLE);
            edtIcerik.setVisibility(View.VISIBLE);

            edtIcerik.setText(txtIcerik.getText());
            edtYazar.setText(txtYazar.getText());
            edtBaslik.setText(txtBaslik.getText());
            edtKonum.setText(txtKonum.getText());
        }
        return true;
    }
}
