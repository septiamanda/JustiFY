package com.ptb.justify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private static final int PReqCode = 2;
    private static  final int REQUESCODE = 2;

    FirebaseAuth auth;
    FirebaseUser currentUser;
    private Uri pickedImgUri = null;

    private CardView ic1, ic2, ic3, ic4, ic5, ic6, ic7, ic8, ic9;
    ImageView ic10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView nama = findViewById(R.id.home_username);
        ImageView profile = findViewById(R.id.profile);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        nama.setText(currentUser.getDisplayName());
        Glide.with(this).load(currentUser.getPhotoUrl()).into(profile);

        ic1 = findViewById(R.id.home_korupsi);
        ic2 = findViewById(R.id.home_faq);
        ic3 = findViewById(R.id.home_aspirasi);
        ic4 = findViewById(R.id.home_forum);
        ic5 = findViewById(R.id.home_lokasi);
        ic6 = findViewById(R.id.home_game);
        ic7 = findViewById(R.id.home_artikel);
        ic8 = findViewById(R.id.home_berita);
        ic9 = findViewById(R.id.home_uu);
        ic10 = findViewById(R.id.logout);

        // ini adalah sebuah intent perpindahan ke halaman lain yg dituju saat di klik
        ic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Ini Adalah Tentang Korupsi", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), TentangKorupsi.class);
                startActivity(home);
            }
        });
        ic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "FaQ", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), FaqActivity.class);
                startActivity(home);
            }
        });
        ic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Aspirasi", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), AspirasiActivity.class);
                startActivity(home);
            }
        });
        ic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Selamat Datang di Forum Aspirasi", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), ForumAspirasiActivity.class);
                startActivity(home);
            }
        });

        ic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Ayo Temukan", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), Lokasi.class);
                startActivity(home);
            }
        });
        ic6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Selamat Bermain", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), PilihangandaActivity.class);
                startActivity(home);
            }
        });
        ic7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Artikel", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), ArtikelActivity.class);
                startActivity(home);
            }
        });
        ic8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Berita", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), Berita.class);
                startActivity(home);
            }
        });
        ic9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Undang-Undang", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), UU.class);
                startActivity(home);
            }
        });

        ic10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(home);
            }
        });
    }
}
