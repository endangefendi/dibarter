package com.dibarter.model;

import android.graphics.drawable.Drawable;

public class BarangModel {

    private String foto;
    private String judul;
    private String lokasi;
    private String deskripsi ;
    private String tgl;
    private int Drfoto;

    public BarangModel(String foto, String judul, String lokasi,String deskripsi , String tgl) {
        this.foto = foto;
        this.judul = judul;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
        this.tgl = tgl;
    }

    public BarangModel(int Drfoto, String judul, String lokasi, String deskripsi , String tgl) {
        this.Drfoto = Drfoto;
        this.judul = judul;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
        this.tgl = tgl;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTgl() {
        return tgl;
    }


    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public int getDrfoto() {
        return Drfoto;
    }

    public void setDrfoto(int drfoto) {
        Drfoto = drfoto;
    }
}
