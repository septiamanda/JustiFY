package com.ptb.justify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RiwayatArtikel extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ArtikelClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    AdapterRiwayatArtikel adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_artikel);

        ImageView btnclose = findViewById(R.id.balikRiwayatArtikel);
        recyclerView = findViewById(R.id.rv_riwayatArtikel);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(RiwayatArtikel.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(RiwayatArtikel.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        final AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        adapter = new AdapterRiwayatArtikel(RiwayatArtikel.this, dataList);
        recyclerView.setAdapter(adapter);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Android Artikel").child(uid);
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    ArtikelClass artikelClass = itemSnapshot.getValue(ArtikelClass.class);
                    if (artikelClass != null) {
                        artikelClass.setKey(itemSnapshot.getKey());
                        dataList.add(artikelClass);
                    }
                }
                adapter.setDatalist(dataList);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RiwayatArtikel.this, ArtikelActivity.class);
                startActivity(intent);
            }
        });

    }
}
