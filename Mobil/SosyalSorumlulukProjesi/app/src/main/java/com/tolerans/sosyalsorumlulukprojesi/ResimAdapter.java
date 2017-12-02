package com.tolerans.sosyalsorumlulukprojesi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by SAMET on 30.11.2017.
 */

public class ResimAdapter extends RecyclerView.Adapter<ResimAdapter.MyHolder> {

    JSONArray resimList;
    Context ctx;


    public ResimAdapter(JSONArray resimList, Context ctx) {
        this.resimList = resimList;
        this.ctx = ctx;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.activity_resim_recyclerview,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        try {
            Picasso.with(ctx).load(resimList.getJSONObject(position).getString("")).into(holder.imgView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        public MyHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.imgRcResim);
        }
    }
}
