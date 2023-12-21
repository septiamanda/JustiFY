package com.ptb.justify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UU extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterUU adapter;
    List<UUClass> dataList;
    UUClass uuData;
    ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uu);

        recyclerView = findViewById(R.id.rv_uu);
        close = findViewById(R.id.imageView);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UU.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(UU.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>(); // Inisialisasi dataList

        uuData = new UUClass(R.string.uu1, R.string.det_uu1);
        dataList.add(uuData);
        uuData = new UUClass(R.string.uu2, R.string.det_uu2);
        dataList.add(uuData);
        uuData = new UUClass(R.string.uu3, R.string.det_uu3);
        dataList.add(uuData);
        uuData = new UUClass(R.string.uu4, R.string.det_uu4);
        dataList.add(uuData);
        uuData = new UUClass(R.string.uu5, R.string.det_uu5);
        dataList.add(uuData);
        uuData = new UUClass(R.string.uu6, R.string.det_uu6);
        dataList.add(uuData);

        // Inisialisasi adapter
        adapter = new AdapterUU(UU.this, dataList);

        // Set adapter ke RecyclerView
        recyclerView.setAdapter(adapter);
    }
}