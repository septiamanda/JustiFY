package com.ptb.justify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AspirasiDetailActivity extends AppCompatActivity {

    TextView detailtitle, detaildesc, detailalamat, detailHapusText, detailEditText, detailTime;
    ImageView detailimage, detailkembali;

    FloatingActionButton detailHapus, detailEdit;

    ExtendedFloatingActionButton detailFab;

    String key = "";
    String imageUrl = "";

    Boolean isAllFabsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspirasi_detail);

        detaildesc = findViewById(R.id.detail_isi);
        detailtitle = findViewById(R.id.detail_judul);
        detailimage = findViewById(R.id.detail_gambar);
        detailalamat = findViewById(R.id.detail_domisili);
        detailFab = findViewById(R.id.detail_fab);
        detailHapus = findViewById(R.id.detail_hapus);
        detailEdit = findViewById(R.id.detail_edit);
        detailHapusText = findViewById(R.id.detail_hapus_text);
        detailEditText = findViewById(R.id.detail_edit_text);
        detailkembali = findViewById(R.id.detail_kembali);
        detailTime = findViewById(R.id.detail_time);

        detailHapus.setVisibility(View.GONE);
        detailEdit.setVisibility(View.GONE);
        detailHapusText.setVisibility(View.GONE);
        detailEditText.setVisibility(View.GONE);

        isAllFabsVisible = false;

        detailFab.shrink();

        detailFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible) {
                    detailHapus.show();
                    detailEdit.show();
                    detailHapusText.setVisibility(View.VISIBLE);
                    detailEditText.setVisibility(View.VISIBLE);

                    detailFab.extend();

                    isAllFabsVisible = true;
                } else {
                    detailHapus.hide();
                    detailEdit.hide();
                    detailHapusText.setVisibility(View.GONE);
                    detailEditText.setVisibility(View.GONE);

                    detailFab.shrink();

                    isAllFabsVisible = false;
                }
            }
        });

        detailkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aspirasi = new Intent(getApplicationContext(), AspirasiActivity.class);
                startActivity(aspirasi);
            }
        });

        // key berasal dari String DataActivity.java
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Glide.with(this).load(bundle.getString("gambar")).into(detailimage);
            detailtitle.setText(bundle.getString("title"));
            detailalamat.setText(bundle.getString("alamat"));
            detaildesc.setText(bundle.getString("desc"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("gambar");
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        detailHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorial").child(uid).child(key);

                // Hapus dari Firebase Database
                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            // Jika penghapusan dari database berhasil, hapus dari Firebase Storage
                            FirebaseStorage storage = FirebaseStorage.getInstance();
                            StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);

                            storageReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(AspirasiDetailActivity.this, "Data dan Gambar Behasil Dihapus", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), AspirasiActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(AspirasiDetailActivity.this, "Terjadi Kesalahan Saat Menghapus Gambar", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(AspirasiDetailActivity.this, "Terjadi Kesalahan Saat Menghapus Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        detailEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AspirasiDetailActivity.this, UpdateActivity.class)
                        .putExtra("Title", detailtitle.getText().toString())
                        .putExtra("Desc", detaildesc.getText().toString())
                        .putExtra("Alamat", detailalamat.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });

        String date = timestampToString(getIntent().getExtras().getLong("AspirasiDate"));
        detailTime.setText(date);
    }


    private String timestampToString(Long time) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", calendar).toString();
        return date;
    }
}