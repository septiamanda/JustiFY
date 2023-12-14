package com.ptb.justify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailUU extends AppCompatActivity {
    TextView detTitle, detDesc;
    ImageView close;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_uu);

        detTitle = findViewById(R.id.det_judul);
        detDesc = findViewById(R.id.det_isi);
        close = findViewById(R.id.imageView1);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detTitle.setText(bundle.getInt("Title"));
            detDesc.setText(bundle.getInt("Desc"));
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailUU.this, UU.class);
                startActivity(intent);
            }
        });
    }
}