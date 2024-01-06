package com.ptb.justify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Locale;

public class DetailArtikel extends AppCompatActivity {

    ImageView btnclose, gambar;
    TextView judul, isi, time, uname;
    String key = "";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_artikel);

        btnclose = findViewById(R.id.balikDetailArtikel);
        gambar = findViewById(R.id.detImgArtikel);
        judul = findViewById(R.id.detJudulArtikel);
        isi = findViewById(R.id.detIsiArtikel);
        time = findViewById(R.id.time);
        uname = findViewById(R.id.username);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Glide.with(this).load(bundle.getString("Gambar")).into(gambar);
            judul.setText(bundle.getString("Judul"));
            isi.setText(bundle.getString("Isi"));
            uname.setText(bundle.getString("Uname"));
            long timestamp = bundle.getLong("time");
            String date = timestampToString(timestamp);
            time.setText(date);
            imageUrl = bundle.getString("Image");
            key = bundle.getString("Key");

        }
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailArtikel.this, ArtikelActivity.class);
                startActivity(intent);
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
