package com.ptb.justify;

import android.text.format.DateFormat;

import com.google.firebase.database.ServerValue;

import java.util.Calendar;
import java.util.Locale;

public class BeritaClass {

    private String beritaTitle;
    private String beritaDesc;
    private String beritaImage;
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

    public String getBeritaTitle() {
        return beritaTitle;
    }

    public String getBeritaDesc() {
        return beritaDesc;
    }

    public String getBeritaImage() {
        return beritaImage;
    }

    public BeritaClass(String beritaTitle, String beritaDesc, String beritaImage) {
        this.beritaTitle = beritaTitle;
        this.beritaDesc = beritaDesc;
        this.beritaImage = beritaImage;
    }

    public BeritaClass(String beritaTitle, String beritaDesc, String beritaImage, String userPhoto, String username, String uid) {
        this.beritaTitle = beritaTitle;
        this.beritaDesc = beritaDesc;
        this.beritaImage = beritaImage;
        this.userPhoto = userPhoto;
        this.username = username;
        this.uid = uid;
        this.timestamp = ServerValue.TIMESTAMP;
    }

    public void setBeritaTitle(String beritaTitle) {
        this.beritaTitle = beritaTitle;
    }

    public void setBeritaDesc(String beritaDesc) {
        this.beritaDesc = beritaDesc;
    }

    public void setBeritaImage(String beritaImage) {
        this.beritaImage = beritaImage;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    public BeritaClass(){

    }
}
