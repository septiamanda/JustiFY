package com.ptb.justify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SkorActivity extends AppCompatActivity {

    private ImageView balikSkor;
    private TextView jawabanBenar;
    private Button ulangPermainan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor);

        balikSkor = findViewById(R.id.balikSkor);
        jawabanBenar = findViewById(R.id.jawabanBenar);
        ulangPermainan = findViewById(R.id.ulang_permainan);

        int skor = getIntent().getIntExtra("Skor Anda adalah", 0);
        jawabanBenar.setText(String.valueOf(skor));

        balikSkor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SkorActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });
        ulangPermainan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SkorActivity.this, PilihangandaActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
