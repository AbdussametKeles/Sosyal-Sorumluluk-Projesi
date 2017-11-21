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

    String bundleJson;
    RecyclerView recyclerView;
    List<Projeler> projelerList;
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


        Bundle bundle = getIntent().getExtras();


        if(bundle!= null){
            bundleJson =bundle.getString("jsonveri");
            Toast.makeText(this,bundleJson,Toast.LENGTH_LONG).show();
            //navigation bardaki butonlara ulaşmamızı sağlayacak
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_cikis).setVisible(true);
            menu.findItem(R.id.nav_proje_ekle).setVisible(true);
            menu.findItem(R.id.nav_profil).setVisible(true);
        }


        recyclerView = (RecyclerView) findViewById(R.id.rvProjeler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        projelerList = new ArrayList<>();

        //projeleriCek();
    }

    public void projeleriCek(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "projeler için url gir", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for(int i=0;i<array.length();i++){

                        JSONObject proje = array.getJSONObject(i);

                        //buraya projeler classından bir nesne oluşturacağız ve
                        //projelerList.add(new Proje(proje.getInt("id"))) şeklinde olacak

                    }
                    MyAdapter adapter = new MyAdapter(MainActivity.this,projelerList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
