package com.tolerans.sosyalsorumlulukprojesi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by SAMET on 01.12.2017.
 */

public class YorumAdapter extends RecyclerView.Adapter<YorumAdapter.MyYorumHolder> {

    JSONArray yorumlar;
    Context c;
    public YorumAdapter(JSONArray yorumlar, Context c) {
        this.yorumlar = yorumlar;
        this.c = c;
    }


    @Override
    public MyYorumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.activity_yorumlar_recycler,parent,false);
        return new MyYorumHolder(view);
    }

    @Override
    public void onBindViewHolder(MyYorumHolder holder, int position) {
        try {
            holder.txtYazarAdi.setText(yorumlar.getJSONObject(position).getString("yazar_id"));
            holder.txtYorumIcerik.setText(yorumlar.getJSONObject(position).getString("yorum_icerik"));
            holder.txtYorumTarih.setText(yorumlar.getJSONObject(position).getString("tarih"));
            Picasso.with(c).load(yorumlar.getJSONObject(position).getString("url")).into(holder.yorumFoto);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyYorumHolder extends RecyclerView.ViewHolder{
        ImageView yorumFoto;
        TextView txtYazarAdi,txtYorumIcerik,txtYorumTarih;
        public MyYorumHolder(View itemView) {
            super(itemView);
            yorumFoto = (ImageView) itemView.findViewById(R.id.imgYorumFoto);
            txtYazarAdi = (TextView) itemView.findViewById(R.id.txtYorumKullanici);
            txtYorumIcerik = (TextView) itemView.findViewById(R.id.txtYorumIcerik);
            txtYorumTarih = (TextView) itemView.findViewById(R.id.txtYorumTarih);
        }
    }
}
