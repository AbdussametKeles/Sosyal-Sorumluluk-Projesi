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

import java.util.List;

/**
 * Created by SAMET on 01.12.2017.
 */

public class YorumAdapter extends RecyclerView.Adapter<YorumAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtKullanici,txtTarih,txtIcerik;

        public ViewHolder(View view) {
            super(view);
            txtKullanici = (TextView) view.findViewById(R.id.txtYorumKullanici);
            txtIcerik = (TextView) view.findViewById(R.id.txtYorumIcerik);
            txtTarih = (TextView) view.findViewById(R.id.txtYorumTarih);
        }
    }

    List<String> list_projeler;
    Context c;

    public YorumAdapter(List<String> list_projeler,Context c) {
        this.c =c;
        this.list_projeler = list_projeler;
    }


    @Override
    public YorumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_yorumlar_recycler, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        return view_holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {



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
