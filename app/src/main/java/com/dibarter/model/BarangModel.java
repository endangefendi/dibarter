package com.dibarter.model;

public class BarangModel {

    private int no;
    private int item_id;
    private String item_title;
    private String item_desc;
    private String item_highlight;
    private String item_gambar_utama;
    private String item_wilayah;
    private String item_tanggal;
    private int item_views;

    public BarangModel(int no, int item_id, String item_title, String item_desc, String item_highlight, String item_gambar_utama,
                       String item_wilayah, String item_tanggal, int item_views) {
        this.no = no;
        this.item_id = item_id;
        this.item_title = item_title;
        this.item_desc = item_desc;
        this.item_highlight = item_highlight;
        this.item_gambar_utama = item_gambar_utama;
        this.item_wilayah = item_wilayah;
        this.item_tanggal = item_tanggal;
        this.item_views = item_views;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public String getItem_highlight() {
        return item_highlight;
    }

    public void setItem_highlight(String item_highlight) {
        this.item_highlight = item_highlight;
    }

    public String getItem_gambar_utama() {
        return item_gambar_utama;
    }

    public void setItem_gambar_utama(String item_gambar_utama) {
        this.item_gambar_utama = item_gambar_utama;
    }

    public String getItem_wilayah() {
        return item_wilayah;
    }

    public void setItem_wilayah(String item_wilayah) {
        this.item_wilayah = item_wilayah;
    }

    public String getItem_tanggal() {
        return item_tanggal;
    }

    public void setItem_tanggal(String item_tanggal) {
        this.item_tanggal = item_tanggal;
    }

    public int getItem_views() {
        return item_views;
    }

    public void setItem_views(int item_views) {
        this.item_views = item_views;
    }
}
