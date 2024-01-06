package com.ptb.justify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ArtikelActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ArtikelClass> dataList;
    DatabaseReference databaseReference;
    AdapterArtikel adapter;
    ValueEventListener eventListener;
    SearchView searchView;
    ImageView btnclose, tambah, riwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        tambah = findViewById(R.id.tambah);
        riwayat = findViewById(R.id.history);
        btnclose = findViewById(R.id.btnclose);
        recyclerView = findViewById(R.id.rv_artikel);
        searchView = findViewById(R.id.searchartikel);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ArtikelActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(ArtikelActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        adapter = new AdapterArtikel(ArtikelActivity.this, dataList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Android Artikel");
        dialog.show();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    String uid = itemSnapshot.getKey();
                    for (DataSnapshot artikelSnapshot : itemSnapshot.getChildren()){
                        ArtikelClass artikelClass = artikelSnapshot.getValue(ArtikelClass.class);
                        if(artikelClass != null){
                            artikelClass.setKey(artikelSnapshot.getKey());
                            dataList.add(artikelClass);
                            artikelClass.setUid(uid);
                        }
                        adapter.setDataList(dataList);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArtikelActivity.this, RiwayatArtikel.class);
                startActivity(intent);
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArtikelActivity.this, TambahArtikel.class);
                startActivity(intent);
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArtikelActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void searchList(String query) {
        ArrayList<ArtikelClass> searchList = new ArrayList<>();
        for (ArtikelClass artikel : dataList) {
            if (artikel.getArtikelJudul().toLowerCase().contains(query.toLowerCase())) {
                searchList.add(artikel);
            }
        }
        adapter.searchDataList(searchList);
    }
}
