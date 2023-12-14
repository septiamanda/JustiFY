package com.ptb.justify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private CardView ic1, ic2, ic3, ic4, ic5, ic6, ic7, ic8, ic9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ic1 = findViewById(R.id.home_korupsi);
        ic2 = findViewById(R.id.home_faq);
        ic3 = findViewById(R.id.home_aspirasi);
        ic4 = findViewById(R.id.home_forum);
        ic5 = findViewById(R.id.home_riwayat);
        ic6 = findViewById(R.id.home_game);
        ic7 = findViewById(R.id.home_artikel);
        ic8 = findViewById(R.id.home_berita);
        ic9 = findViewById(R.id.home_uu);

        // ini adalah sebuah intent perpindahan ke halaman lain yg dituju saat di klik
        ic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });
        ic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });
        ic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), AspirasiActivity.class);
                startActivity(home);
            }
        });
        ic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });
        ic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });
        ic6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });
        ic7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });
        ic8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });
        ic9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });
    }
}