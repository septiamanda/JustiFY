package com.ptb.justify;

public class BeritaClass {

    private String beritaTitle;
    private String beritaDesc;
    private String beritaImage;
    private String key;

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

    public BeritaClass(){

    }
}
