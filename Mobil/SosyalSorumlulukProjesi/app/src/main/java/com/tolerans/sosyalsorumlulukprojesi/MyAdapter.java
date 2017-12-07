package com.tolerans.sosyalsorumlulukprojesi;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAMET on 11.11.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    String json;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView yazar,konum,baslik,icerik;
        LinearLayout linearLayout;
        ImageView resim;

        public ViewHolder(View view) {
            super(view);


            yazar = (TextView)view.findViewById(R.id.holderKullanici);
            icerik = (TextView)view.findViewById(R.id.holderIcerik);
            konum = (TextView) view.findViewById(R.id.holderKonum);
            baslik = (TextView) view.findViewById(R.id.holderBaslik);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearRecycler);
            resim = (ImageView) view.findViewById(R.id.holderResim);


        }
    }

    List<Projeler> list_projeler;
    Context c;

    public MyAdapter(List<Projeler> list_projeler,Context c,String json) {
        this.c =c;
        this.list_projeler = list_projeler;
        this.json = json;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_projeler, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        return view_holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        List<String> resimler = (ArrayList<String>) list_projeler.get(position).getResimler();
        if(resimler != null && resimler.size() != 0 && !resimler.contains(null) && !resimler.contains("")){
        }
        holder.yazar.setText(String.valueOf(list_projeler.get(position).getYazarId()));
        holder.yazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://service.sosyalsorumluluk.mansetler.org/kullanici/goruntule?kullanici_id="+String.valueOf(list_projeler.get(position).getYazarId()), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(c,BaskaProfilActivity.class);
                        intent.putExtra("kullaniciJson",response);
                        c.startActivity(intent);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(c,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
                Volley.newRequestQueue(c).add(stringRequest);
            }
        });
        holder.baslik.setText(list_projeler.get(position).getBaslik());
        holder.icerik.setText(list_projeler.get(position).getIcerik());
        holder.konum.setText(String.valueOf(list_projeler.get(position).getKonumID()));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c,ProjeIcerikActivity.class);
                intent.putExtra("json",json);
                intent.putExtra("proje",  list_projeler.get(position));
                c.startActivity(intent);
            }
        });
        Picasso.with(c).load(list_projeler.get(position).getResimUrl()).placeholder(R.mipmap.ic_launcher).into(holder.resim);

    }

    @Override
    public int getItemCount() {
        return list_projeler.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}