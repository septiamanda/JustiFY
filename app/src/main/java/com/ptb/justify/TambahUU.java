package com.ptb.justify;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahUU {
    public static void tambahData() {
        // Inisialisasi Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Mendapatkan referensi ke node di Firebase Realtime Database
        DatabaseReference uuRef = database.getReference("Android UU");

        // Menambahkan data UU ke database
        tambahUU(uuRef, "UU No 30 tentang korupsi", "Korupsi adalah perbuatan yang tercela");
        tambahUU(uuRef, "UU No 34 tentang pemberantasan korupsi", "Korupsi adalah perbuatan yang tercela");
        tambahUU(uuRef, "UU No 25 tentang kpk", "KPK adalah lembaga");
    }

    private static void tambahUU(DatabaseReference uuRef, String judul, String deskripsi) {
        // Membuat objek data UUClass
        //UUClass uuData = new UUClass(judul, deskripsi);

        // Menambahkan data ke database
        //uuRef.push().setValue(uuData);
    }

}
