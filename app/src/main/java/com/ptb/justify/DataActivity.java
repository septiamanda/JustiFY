package com.ptb.justify;

import android.text.format.DateFormat;

import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DataActivity {
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }



    public DataActivity(String userPhoto, String username, String imageURL, String title, String alamat, String desc, String uid) {
        this.userPhoto = userPhoto;
        this.username = username;
        this.imageURL = imageURL;
        this.title = title;
        this.alamat = alamat;
        this.desc = desc;
        this.uid = uid;
        this.timestamp = ServerValue.TIMESTAMP;
    }

    public DataActivity(){

    }

    private String userPhoto;
    private String username;
    private String imageURL;
    private String title;
    private String alamat;
    private String desc;
    private String key;
    private String uid;
    private Object timestamp;
}
