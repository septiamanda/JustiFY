package com.ptb.justify;

import android.text.format.DateFormat;

import com.google.firebase.database.ServerValue;

import java.util.Calendar;
import java.util.Locale;

public class ArtikelClass {

    private String artikelJudul;
    private String artikelDeskripsi;
    private String artikelImage;
    private String key;
    private String userPhoto;
    private String username;
    private String uid;
    private Object timestamp;

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getFormatedDate() {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis((Long) timestamp);
        String date = DateFormat.format("dd-MM-yyyy", calendar).toString();
        return date;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public Object getTimestamp() {
        return timestamp;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getArtikelJudul() {
        return artikelJudul;
    }
    public String getArtikelDeskripsi() {
        return artikelDeskripsi;
    }
    public String getArtikelImage() {
        return artikelImage;
    }

    public ArtikelClass(String artikelJudul, String artikelDeskripsi, String artikelImage, String uphoto) {
        this.artikelJudul = artikelJudul;
        this.artikelDeskripsi = artikelDeskripsi;
        this.artikelImage = artikelImage;
    }

    public ArtikelClass(String artikelJudul, String artikelDeskripsi, String artikelImage, String userPhoto, String username, String uid) {
        this.artikelJudul = artikelJudul;
        this.artikelDeskripsi = artikelDeskripsi;
        this.artikelImage = artikelImage;
        this.userPhoto = userPhoto;
        this.username = username;
        this.uid = uid;
        this.timestamp = ServerValue.TIMESTAMP;
    }

    public void setArtikelJudul(String artikelJudul) {
        this.artikelJudul = artikelJudul;
    }

    public void setArtikelDeskripsi(String artikelDeskripsi) {
        this.artikelDeskripsi = artikelDeskripsi;
    }

    public void setArtikelImage(String artikelImage) {
        this.artikelImage = artikelImage;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    public ArtikelClass(){

    }
}
