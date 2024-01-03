package com.ptb.justify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class ForumDetailActivity extends AppCompatActivity {

    TextView detailtitleF, detaildescF, detailalamatF, detailTimeF, detailUsernameF;
    ImageView detailimageF, detailkembaliF, detailProfileF;
    String key = "";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_detail);

        detailProfileF = findViewById(R.id.detail_profile_forum);
        detailUsernameF = findViewById(R.id.detail_username_forum);
        detailTimeF = findViewById(R.id.detail_time_forum);
        detailalamatF = findViewById(R.id.detail_domisili_forum);
        detailimageF = findViewById(R.id.detail_gambar_forum);
        detailtitleF = findViewById(R.id.detail_judul_forum);
        detaildescF = findViewById(R.id.detail_isi_forum);
        detailkembaliF = findViewById(R.id.detail_kembaliForum);

        detailkembaliF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forum = new Intent(getApplicationContext(), ForumAspirasiActivity.class);
                startActivity(forum);
            }
        });

        // key berasal dari String DataActivity.java
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Glide.with(this).load(bundle.getString("gambar")).into(detailimageF);
            detailtitleF.setText(bundle.getString("title"));
            detailalamatF.setText(bundle.getString("alamat"));
            detaildescF.setText(bundle.getString("desc"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
    }
}