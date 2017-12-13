package com.tolerans.sosyalsorumlulukprojesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IletisimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iletisim);

        final EditText icerik,baslik,mail,ad;
        Button gonder;


        icerik = (EditText) findViewById(R.id.edtIletisimIcerik);
        baslik = (EditText) findViewById(R.id.edtIletisimBaslik);
        mail = (EditText) findViewById(R.id.edtIletisimMail);
        ad = (EditText) findViewById(R.id.edtIletisimAd);
        gonder = (Button) findViewById(R.id.iletisimGonder);


        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, "http://service.sosyalsorumluluk.mansetler.org/contact/iletisimformu", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplication(),response.toString(),Toast.LENGTH_LONG).show();
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
                        params.put("adi_soyadi",ad.getText().toString());
                        params.put("eposta",mail.getText().toString());
                        params.put("konu",baslik.getText().toString());
                        params.put("message",icerik.getText().toString());

                        return params;
                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(request);

            }
        });


    }
}
