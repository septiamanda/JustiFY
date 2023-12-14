package com.ptb.justify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class AspirasiActivity extends AppCompatActivity {

    // Halaman ini untuk menmapilkan hasil RecyclerView yang telah dibuat, jadi halaman ini untu RecyclerView nya
    FloatingActionButton fab;
    RecyclerView recyclerView;
    List<DataActivity> datalist;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    AdapterActivity adapterActivity;
    SearchView searchView;
    ImageView kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspirasi);

        kembali = findViewById(R.id.aspirasi_kembali);
        recyclerView = findViewById(R.id.aspirasi_recyclerview);
        fab = findViewById(R.id.anspirasi_fab);
        searchView = findViewById(R.id.aspirasi_cari);
        searchView.clearFocus();

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(AspirasiActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(AspirasiActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.activity_progres);
        AlertDialog dialog = builder.create();
        dialog.show();

        datalist = new ArrayList<>();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorial").child(uid);

        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datalist.clear();  // Menghapus data dari list kelas
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {
                    DataActivity dataActivity = itemSnapshot.getValue(DataActivity.class);
                    dataActivity.setKey(itemSnapshot.getKey());
                    datalist.add(dataActivity);
                }
                adapterActivity.setDatalist(datalist);  // Menggunakan list kelas
                adapterActivity.notifyDataSetChanged();
                dialog.dismiss();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                dialog.dismiss();
            }
        });

        adapterActivity = new AdapterActivity(AspirasiActivity.this, datalist);
        recyclerView.setAdapter(adapterActivity);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cariList(newText);
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AspirasiActivity.this, TambahAspirasiActivity.class);
                startActivity(intent);
            }
        });
    }

    public void cariList(String text) {
        ArrayList<DataActivity> cariList = new ArrayList<>();
        for (DataActivity dataActivity: datalist) {
            if (dataActivity.getTitle().toLowerCase().contains(text.toLowerCase())) {
                cariList.add(dataActivity);
            }
        }

        adapterActivity.cariDataList(cariList);
    }
}