package com.ptb.justify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Berita extends AppCompatActivity {

    RecyclerView recyclerView;
    List<BeritaClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    MyAdapter adapter;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView history = findViewById(R.id.history);
        ImageView close = findViewById(R.id.btnclose);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Berita.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(Berita.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        adapter = new MyAdapter(Berita.this, dataList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Android Berita");
        dialog.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    String uid = itemSnapshot.getKey();
                    for (DataSnapshot beritaSnapshot : itemSnapshot.getChildren()){
                        BeritaClass beritaClass = beritaSnapshot.getValue(BeritaClass.class);
                        if(beritaClass != null){
                            beritaClass.setKey(beritaSnapshot.getKey());
                            dataList.add(beritaClass);
                            beritaClass.setUid(uid);
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

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Berita.this, RiwayatBerita.class);
                startActivity(intent);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Berita.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Set a click listener on the ImageView
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Berita.this, TambahBeritaActivity.class);
                startActivity(intent);
            }
        });
    }
    public void searchList(String text){
        ArrayList<BeritaClass> searchList = new ArrayList<>();
        for(BeritaClass beritaClass: dataList){
            if (beritaClass.getBeritaTitle().toLowerCase().contains(text.toLowerCase())){
                searchList.add(beritaClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}