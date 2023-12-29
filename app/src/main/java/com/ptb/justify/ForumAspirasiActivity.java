package com.ptb.justify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class ForumAspirasiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataActivity> datalist;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    AdapterForumAspirasi adapterForumAspirasi;
    SearchView searchView;
    ImageView kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_aspirasi);

        kembali = findViewById(R.id.forum_kembali);
        recyclerView = findViewById(R.id.forum_recyclerview);
        searchView = findViewById(R.id.forum_cari);
        searchView.clearFocus();

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(ForumAspirasiActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(ForumAspirasiActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.activity_progres);
        AlertDialog dialog = builder.create();
        dialog.show();

        datalist = new ArrayList<>();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorial");

        dialog.show();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Called when the data is successfully fetched

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Iterasi semua child (user) di bawah "Android Tutorial"
                    String uid = userSnapshot.getKey(); // Mendapatkan UID user

                    for (DataSnapshot aspirasiSnapshot : userSnapshot.getChildren()) {

                        // Iterasi semua child (data aspirasi) di bawah UID user
                        DataActivity dataActivity = aspirasiSnapshot.getValue(DataActivity.class);

                        if (dataActivity != null) {
                            dataActivity.setKey(aspirasiSnapshot.getKey());
                            datalist.add(dataActivity);
                            dataActivity.setUid(uid);
                        }
                        adapterForumAspirasi.setDatalist(datalist);  // Menggunakan list kelas
                        adapterForumAspirasi.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle kesalahan jika terjadi
                Log.e("Firebase", "Error reading data: " + databaseError.getMessage());
            }
        });

        adapterForumAspirasi = new AdapterForumAspirasi(ForumAspirasiActivity.this, datalist);
        recyclerView.setAdapter(adapterForumAspirasi);

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
    }

    public void cariList(String text) {
        ArrayList<DataActivity> cariList = new ArrayList<>();
        for (DataActivity dataActivity: datalist) {
            if (dataActivity.getTitle().toLowerCase().contains(text.toLowerCase())) {
                cariList.add(dataActivity);
            }
        }

        adapterForumAspirasi.cariDataList(cariList);
    }
}