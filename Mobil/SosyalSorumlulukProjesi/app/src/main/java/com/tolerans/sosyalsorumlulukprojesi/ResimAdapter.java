package com.tolerans.sosyalsorumlulukprojesi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAMET on 30.11.2017.
 */

public class ResimAdapter extends RecyclerView.Adapter<ResimAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;

        public ViewHolder(View view) {
            super(view);
            imgView = (ImageView) view.findViewById(R.id.imgRcResim);
        }
    }

    List<String> list_projeler;
    Context c;

    public ResimAdapter(List<String> list_projeler,Context c) {
        this.c =c;
        this.list_projeler = list_projeler;
    }


    @Override
    public ResimAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_resim_recyclerview, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        return view_holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Picasso.with(c).load(list_projeler.get(position)).placeholder(R.mipmap.ic_launcher).into(holder.imgView);

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
