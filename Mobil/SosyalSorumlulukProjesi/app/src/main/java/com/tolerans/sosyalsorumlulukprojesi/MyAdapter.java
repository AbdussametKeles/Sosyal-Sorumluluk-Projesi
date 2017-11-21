package com.tolerans.sosyalsorumlulukprojesi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SAMET on 11.11.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context c;
    List<Projeler> projelerList;
    public MyAdapter(Context c, List<Projeler> projelerList){
        this.c = c;
        this.projelerList = projelerList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View view = layoutInflater.inflate(R.layout.activity_projeler,null);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // bu kısımda recyclerview tek tek arraydeki projeleri oluşturacak ve o pozisyondaki verileri
        //gerekli konumlara atacak

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView holderBaslik, holderIcerik, holderKullanici, holderKonum;
        ImageView holderResim;

        public MyViewHolder(View itemView) {
            super(itemView);
            holderBaslik = (TextView) itemView.findViewById(R.id.holderBaslik);
            holderIcerik = (TextView) itemView.findViewById(R.id.holderIcerik);
            holderKullanici = (TextView) itemView.findViewById(R.id.holderKullanici);
            holderKonum = (TextView) itemView.findViewById(R.id.holderKonum);

        }
    }
}
