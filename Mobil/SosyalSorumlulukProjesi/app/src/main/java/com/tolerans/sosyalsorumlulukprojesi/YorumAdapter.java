package com.tolerans.sosyalsorumlulukprojesi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SAMET on 01.12.2017.
 */

public class YorumAdapter extends RecyclerView.Adapter<YorumAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtIcerik;
        EditText edtIcerik;
        Button btnSil,btnDuzenle,btnKaydet;

        public ViewHolder(View view) {
            super(view);
            txtIcerik = (TextView) view.findViewById(R.id.txtYorumIcerik);
            btnSil = (Button) view.findViewById(R.id.btnYorumSil);
            edtIcerik = (EditText) view.findViewById(R.id.edtYorum);
            btnDuzenle = (Button) view.findViewById(R.id.btnYorumDuzenle);
            btnKaydet = (Button) view.findViewById(R.id.btnYorumKaydet);
        }
    }

   List<String> yorumIcerikler;
    Context c;
    int  kullaniciID;
    List<Integer> yorumIDs, kullaniciIDs;
    String token;

    public YorumAdapter(List<String> yorumIcerikler,Context c,int kullaniciID,List<Integer> yorumIDs,List<Integer> kullaniciIDs,String token) {
        this.c =c;
        this.yorumIcerikler = yorumIcerikler;
        this.kullaniciIDs = kullaniciIDs;
        this.yorumIDs = yorumIDs;
        this.kullaniciID = kullaniciID;
        this.token = token;
    }


    @Override
    public YorumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_yorumlar_recycler, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        return view_holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.txtIcerik.setText(yorumIcerikler.get(position));
       // Toast.makeText(c,kullaniciIDs.get(position).toString(),Toast.LENGTH_LONG).show();
        if(kullaniciIDs.get(position)==kullaniciID){
            holder.btnSil.setVisibility(View.VISIBLE);
            holder.btnDuzenle.setVisibility(View.VISIBLE);
        }
        holder.btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,token+" "+String.valueOf(yorumIDs.get(position)),Toast.LENGTH_LONG).show();
                StringRequest request = new StringRequest(Request.Method.POST, "http://service.sosyalsorumluluk.mansetler.org/urunler/yorum_sil", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(c,response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(c,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<>();
                        params.put("token_string",token);
                        params.put("yorum_id",yorumIDs.get(position).toString());
                        return params;

                    }
                };
                Volley.newRequestQueue(c).add(request);
            }
        });
        holder.btnDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.txtIcerik.setVisibility(View.GONE);
                holder.edtIcerik.setVisibility(View.VISIBLE);
                holder.btnKaydet.setVisibility(View.VISIBLE);
                holder.btnDuzenle.setVisibility(View.GONE);
            }
        });
        holder.btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, "http://service.sosyalsorumluluk.mansetler.org/urunler/yorum_duzenle", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(c,response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(c,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("token_string",token);
                        params.put("yorum_id",yorumIDs.get(position).toString());
                        params.put("yorum_icerigi",holder.edtIcerik.getText().toString());
                        return params;
                    }
                };
                Volley.newRequestQueue(c).add(request);
            }
        });

    }

    @Override
    public int getItemCount() {
        return yorumIcerikler.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
