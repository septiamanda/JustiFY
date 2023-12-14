package com.ptb.justify;

public class DataUser {

    String email;
    String telepon;
    String username;

    public String getEmail() {
        return email;
    }

    public String getTelepon() {
        return telepon;
    }

    public String getUsername() {
        return username;
    }

    public DataUser(String email, String telepon, String username) {
        this.email = email;
        this.telepon = telepon;
        this.username = username;
    }
}
