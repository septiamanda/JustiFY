package com.ptb.justify;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataActivity {

    private String imageURL;
    private String title;
    private String alamat;
    private String desc;
    private String key;
    private String uid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKey() {

        return  key;
    }

    public void setKey(String key) {

        this.key = key;
    }

    private long timestamp;

    public void setTimestamp() {

        this.timestamp = new Date().getTime();
    }

    public DataActivity(long timestamp) {

        this.timestamp = timestamp;
    }

    public long getTimestamp() {

        return timestamp;
    }

    // cara menampilkan public String getGambar() ini adalah dengan
    // klik kanan pada private String desc; lalu pilih
    // generate -> constructor -> pilih isinya saja, yg atas jangan -> klik OK
    public String getImageURL() {

        return imageURL;
    }

    public void setImageURL(String imageURL) {

        this.imageURL = imageURL;
    }

    public String getTitle() {

        return title;
    }

    public String getAlamat() {

        return alamat;
    }

    public String getDesc() {

        return desc;
    }



    // cara menampilkan ini dengan klik kanan, pilih generate -> getter -> pilih semua, klik OK
    public DataActivity(String gambar, String title, String alamat, String desc) {
        this.imageURL = gambar;
        this.title = title;
        this.alamat = alamat;
        this.desc = desc;
        this.timestamp = timestamp;
    }

    public DataActivity() {

    }

    // ini format untuk menampilkan timestamp
    public String getFormattedTimestamp() {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
