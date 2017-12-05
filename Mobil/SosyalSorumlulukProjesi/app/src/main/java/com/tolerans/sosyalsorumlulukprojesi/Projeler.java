package com.tolerans.sosyalsorumlulukprojesi;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAMET on 11.11.2017.
 */

public class Projeler implements Serializable {

    String Baslik;
    String yazarAdi;
    String Konum;
    String Tarih;
    String Icerik;


    String resimUrl;

    int    yazarId;
    int konumID;
    int kategoriId;
    int urunID;
    List<String>  yorumlar;
    String Kategori;
    String BagisTipi;
    List<String> resimler;

    public List<String> getResimler() {
        return resimler;
    }

    public void setResimler(List<String> resimler) {
        this.resimler = resimler;
    }




    public int getUrunID() {
        return urunID;
    }

    public void setUrunID(int urunID) {
        this.urunID = urunID;
    }

    public String getBagisTipi() {
        return BagisTipi;
    }

    public void setBagisTipi(String bagisTipi) {
        BagisTipi = bagisTipi;
    }

    public int getKategoriId() {
        return kategoriId;
    }
    public String getResimUrl() {
        return resimUrl;
    }

    public void setResimUrl(String resimUrl) {
        this.resimUrl = resimUrl;
    }


    public void setKategoriId(int kategoriId) {
        this.kategoriId = kategoriId;
    }

    public void setKategori(String kategori) {
        Kategori = kategori;
    }


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

    public List<String> getYorumlar() {
        return yorumlar;
    }

    public void setYorumlar(List<String> yorumlar) {
        this.yorumlar = yorumlar;
    }

}
