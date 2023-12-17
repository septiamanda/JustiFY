package com.ptb.justify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AboutAplikasi extends AppCompatActivity {

    ArrayList<AboutClass> arrayList;
    RecyclerView recyclerView;
    AdapterAbout adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_aplikasi);
        recyclerView = findViewById(R.id.rv_about);
        arrayList = new ArrayList<>();

        arrayList.add(new AboutClass("Tujuan","aplikasi", false));

        adapter = new AdapterAbout(arrayList,AboutAplikasi.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}