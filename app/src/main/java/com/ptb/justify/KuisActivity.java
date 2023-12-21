package com.ptb.justify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class KuisActivity extends AppCompatActivity {

    TextView kuis;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis);

        kuis = findViewById(R.id.pilgan);

        kuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(KuisActivity.this, "Haloo!", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), PilihangandaActivity.class);
                startActivity(home);
            }
        });

        back = findViewById(R.id.imageX);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(KuisActivity.this, "Haloo!", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
            }
        });
    }
}
