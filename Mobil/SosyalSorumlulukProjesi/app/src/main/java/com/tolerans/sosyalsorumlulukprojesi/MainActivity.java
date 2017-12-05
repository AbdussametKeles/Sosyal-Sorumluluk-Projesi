package com.tolerans.sosyalsorumlulukprojesi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String bundleJson,token;
    RecyclerView recyclerView;
    ArrayList<Projeler> projelerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_cikis).setVisible(false);
        menu.findItem(R.id.nav_proje_ekle).setVisible(false);
        menu.findItem(R.id.nav_profil).setVisible(false);


        Bundle bundle = getIntent().getExtras();


        if(bundle!= null){
            bundleJson =bundle.getString("jsonveri");
            Toast.makeText(this,bundleJson,Toast.LENGTH_LONG).show();

            try {
                JSONObject json = new JSONObject(bundleJson);
                token = json.getString("token");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //navigation bardaki butonlara ulaşmamızı sağlayacak
            menu.findItem(R.id.nav_cikis).setVisible(true);
            menu.findItem(R.id.nav_proje_ekle).setVisible(true);
            menu.findItem(R.id.nav_profil).setVisible(true);
        }

        projelerList = new ArrayList<Projeler>();

        recyclerView = (RecyclerView) findViewById(R.id.rvProjeler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recyclerView.setLayoutManager(layoutManager);



        projeleriCek();



    }

    public void projeleriCek(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://service.sosyalsorumluluk.mansetler.org/urunler/listele", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("urunler");

                    for(int i=0;i<array.length();i++){

                        JSONObject projeJson = array.getJSONObject(i);
                        Projeler proje = new Projeler();
                        proje.setBaslik(projeJson.getString("urun_adi"));
                        proje.setIcerik(projeJson.getString("urun_aciklamasi"));
                        proje.setKonumID(projeJson.getInt("urun_konumu"));
                        proje.setUrunID(projeJson.getInt("urun_id"));
                        proje.setYazarId(projeJson.getInt("kullanici_id"));
                        proje.setKategoriId(projeJson.getInt("kategori_id"));
                        proje.setBagisTipi(projeJson.getString("bagis_tipi"));
                        ArrayList<String> resimler = new ArrayList<>();
                        ArrayList<String> yorumIcerikler= new ArrayList<>();
                       // JSONArray yorumArray = new JSONArray(projeJson.getJSONObject("yorumlar"));
                     //  proje.setYorumlar(yorumArray);


                         JSONArray resimArray = projeJson.getJSONArray("gorseller");
                        if(!resimArray.toString().equals("[]")) {
                            String ilkResim = resimArray.getJSONObject(0).getString("resim");
                            ilkResim.replace("\\", "");
                            proje.setResimUrl(ilkResim);
                            for(int j =0; j<resimArray.length();j++){
                                String resimUrl = resimArray.getJSONObject(j).getString("resim");
                                resimUrl.replace("\\","");
                                resimler.add(resimUrl);
                            }
                            proje.setResimler(resimler);
                        }
                        JSONArray yorumArray = projeJson.getJSONArray("yorumlar");
                        if(!yorumArray.toString().equals("[]")) {
                            for(int j =0; j<yorumArray.length();j++){
                                String yorumIcerik = yorumArray.getJSONObject(j).getString("yorum_icerigi");
                                yorumIcerikler.add(yorumIcerik);

                            }
                            proje.setYorumlar(yorumIcerikler);

                        }
                      // Toast.makeText(getApplicationContext(),proje.getYorumlar().get(0),Toast.LENGTH_LONG).show();
                        projelerList.add(proje);

                }


                    MyAdapter adapter = new MyAdapter(projelerList,MainActivity.this,bundleJson);
                    recyclerView.setAdapter(adapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Projeyi çekerken hata oluştu",Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }


    @Override
    public void onBackPressed() {
        //geri tuşuna basıldığında çalışacak method
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        this.setTitle("AnaSayfa");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //toolbara eklediğimiz iconların olduğu menüyü temsil edilyor
        getMenuInflater().inflate(R.menu.main, menu);
        //bundle içindeki veriye bakıp giriş butonunun göörünürlüğünü kapatıyor
        //true yapmamıza gerek yok zaten otomatik olarak her defasında yeniden oluşturuyor.
        if(bundleJson!=null){
            menu.findItem(R.id.action_profil).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Tıklanan itemin hangisi olduğunu seçiyoruz
        //Dilersek tek icon olduğu için if kullanmadanda yapabilirdik.

        int id = item.getItemId();

        if(id == R.id.action_profil) {

            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_proje_ekle) {
            Intent intent = new Intent(MainActivity.this,ProjeEkleActivity.class);
            intent.putExtra("jsonveriler",bundleJson);
            startActivity(intent);
        } else if (id == R.id.nav_profil) {
            Intent intent = new Intent(MainActivity.this,ProfilActivity.class);
            intent.putExtra("jsonveriler",bundleJson);
            startActivity(intent);

        } else if (id == R.id.nav_cikis) {
            //shared preferenceyi temizliyor.
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            finish();
            //uygulamayı baştan başlattık.
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
