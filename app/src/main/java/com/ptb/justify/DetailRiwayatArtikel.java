package com.ptb.justify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.Locale;

public class DetailRiwayatArtikel extends AppCompatActivity {

    ImageView btnclose, gambar, hapus, edit;
    TextView judulArtkl, isiArtkl, time;
    String key = "";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayat_artikel);

        btnclose = findViewById(R.id.btnclose);
        gambar = findViewById(R.id.detImgArtikel);
        judulArtkl = findViewById(R.id.detJudulArtikel);
        isiArtkl = findViewById(R.id.detIsiArtikel);
        time = findViewById(R.id.time);
        hapus = findViewById(R.id.hapusArtikel);
        edit = findViewById(R.id.editArtikel);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            judulArtkl.setText(bundle.getString("Judul"));
            isiArtkl.setText(bundle.getString("Isi"));
            long timestamp = bundle.getLong("time");
            String date = timestampToString(timestamp);
            time.setText(date);
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Gambar");
            Glide.with(this).load(bundle.getString("Gambar")).into(gambar);
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Munculkan dialog konfirmasi sebelum menghapus
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailRiwayatArtikel.this);
                builder.setTitle("Konfirmasi Hapus");
                builder.setMessage("Apakah yakin ingin menghapus artikel ini?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Hapus artikel jika pengguna menekan tombol "Ya"
                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Android Artikel").child(uid).child(key);
                        reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    FirebaseStorage storage = FirebaseStorage.getInstance();
                                    StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);

                                    storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(DetailRiwayatArtikel.this, "Artikel berhasil dihapus", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(DetailRiwayatArtikel.this, RiwayatArtikel.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(DetailRiwayatArtikel.this, "Terjadi kesalahan saat menghapus Gambar", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    Toast.makeText(DetailRiwayatArtikel.this, "Terjadi kesalahan saat menghapus artikel", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Batalkan penghapusan jika pengguna menekan tombol "Tidak"
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailRiwayatArtikel.this, UpdateArtikel.class)
                        .putExtra("Judul", judulArtkl.getText().toString())
                        .putExtra("Isi", isiArtkl.getText().toString())
                        .putExtra("Gambar", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailRiwayatArtikel.this, RiwayatArtikel.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private String timestampToString(long timestamp) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(timestamp);
        String date = DateFormat.format("dd-MM-yyyy", calendar).toString();
        return date;
    }
}
