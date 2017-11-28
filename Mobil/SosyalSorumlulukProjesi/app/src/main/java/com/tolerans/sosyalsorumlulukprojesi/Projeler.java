package com.tolerans.sosyalsorumlulukprojesi;

import java.util.ArrayList;

/**
 * Created by SAMET on 11.11.2017.
 */

public class Projeler {

    String Baslik,yazarAdi,Konum,Tarih,Icerik;
    int    yazarId,konumID;
    ArrayList<Yorum> yorumlar;

    public String getBaslik() {
        return Baslik;
    }

    public void setBaslik(String baslik) {
        Baslik = baslik;
    }

    public String getYazarAdi() {
        return yazarAdi;
    }

    public void setYazarAdi(String yazarAdi) {
        this.yazarAdi = yazarAdi;
    }

    public String getKonum() {
        return Konum;
    }

    public void setKonum(String konum) {
        Konum = konum;
    }

    public String getTarih() {
        return Tarih;
    }

    public void setTarih(String tarih) {
        Tarih = tarih;
    }

    public String getIcerik() {
        return Icerik;
    }

    public void setIcerik(String icerik) {
        Icerik = icerik;
    }

    public int getYazarId() {
        return yazarId;
    }

    public void setYazarId(int yazarId) {
        this.yazarId = yazarId;
    }

    public int getKonumID() {
        return konumID;
    }

    public void setKonumID(int konumID) {
        this.konumID = konumID;
    }

    public ArrayList<Yorum> getYorumlar() {
        return yorumlar;
    }

    public void setYorumlar(ArrayList<Yorum> yorumlar) {
        this.yorumlar = yorumlar;
    }

    public ArrayList<Kategori> getKategori() {
        return kategori;
    }

    public void setKategori(ArrayList<Kategori> kategori) {
        this.kategori = kategori;
    }

    ArrayList<Kategori> kategori;

    public class Yorum{

        String yorumIcerik;
        int yorumID;

        public String getYorumIcerik() {
            return yorumIcerik;
        }

        public void setYorumIcerik(String yorumIcerik) {
            this.yorumIcerik = yorumIcerik;
        }

        public int getYorumID() {
            return yorumID;
        }

        public void setYorumID(int yorumID) {
            this.yorumID = yorumID;
        }
    }
    public class Kategori{
        String kategoriAdi;
        int kategoriID;

        public String getKategoriAdi() {
            return kategoriAdi;
        }

        public void setKategoriAdi(String kategoriAdi) {
            this.kategoriAdi = kategoriAdi;
        }

        public int getKategoriID() {
            return kategoriID;
        }

        public void setKategoriID(int kategoriID) {
            this.kategoriID = kategoriID;
        }
    }

}
