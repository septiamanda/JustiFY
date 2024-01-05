package com.ptb.justify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ForumDetailActivity extends AppCompatActivity {

    TextView detailtitleF, detaildescF, detailalamatF, detailTimeF, detailUsernameF;
    ImageView detailimageF, detailkembaliF, detailProfileF;
    String key = "";
    String imageUrl = "";
    String userPhoto ="";
    RecyclerView RvComment;
    EditText detailKomen;
    Button addKomen;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    CommentAdapter commentAdapter;
    List<Comment> listComment;
    static String COMMENT_KEY = "Comment";
//    List<KomenActivity> listKomen;
//    AdapterKomenForum adapterKomenForum;
//    static String COMMENT_KEY = "Comment" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_detail);

        //Ini Detail Forum
        detailProfileF = findViewById(R.id.detail_profile_forum);
        detailUsernameF = findViewById(R.id.detail_username_forum);
        detailTimeF = findViewById(R.id.detail_time_forum);
        detailalamatF = findViewById(R.id.detail_domisili_forum);
        detailimageF = findViewById(R.id.detail_gambar_forum);
        detailtitleF = findViewById(R.id.detail_judul_forum);
        detaildescF = findViewById(R.id.detail_isi_forum);
        detailkembaliF = findViewById(R.id.detail_kembaliForum);

        //Komentar
        RvComment = findViewById(R.id.detail_recyclerview_Komen);
        detailKomen = findViewById(R.id.detail_Komentar);
        addKomen = findViewById(R.id.detail_add_komen);

        detailkembaliF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forum = new Intent(getApplicationContext(), ForumAspirasiActivity.class);
                startActivity(forum);
            }
        });

        // Mendapatkan data dari Intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Glide.with(this).load(bundle.getString("userprofile")).into(detailProfileF);
            detailUsernameF.setText(bundle.getString("username"));

            // Mengonversi timestamp ke format tanggal yang sesuai untuk detail forum
            long timestamp = bundle.getLong("time");
            String date = timestampToString(timestamp);
            detailTimeF.setText(date);

            detailalamatF.setText(bundle.getString("alamat"));
            Glide.with(this).load(bundle.getString("gambar")).into(detailimageF);
            detailtitleF.setText(bundle.getString("title"));
            detaildescF.setText(bundle.getString("desc"));
            key = bundle.getString("key");
            imageUrl = bundle.getString("gambar");
            userPhoto = bundle.getString("userprofile");
        }

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        // add Comment button click listner

        addKomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addKomen.setVisibility(View.INVISIBLE);
                DatabaseReference commentReference = firebaseDatabase.getReference(COMMENT_KEY).child(key).push();
                String comment_content = detailKomen.getText().toString();
                String uid = firebaseUser.getUid();
                String uname = firebaseUser.getDisplayName();
                String uimg = firebaseUser.getPhotoUrl().toString();
                Comment comment = new Comment(comment_content, uid,uimg,uname);

                commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        showMessage("comment added");
                        detailKomen.setText("");
                        addKomen.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("gagal menambahkan komen : "+e.getMessage());
                    }
                });
            }
        });

        //ini recyclerView Comment
        iniRvComment();
    }

    private void iniRvComment() {

        RvComment.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference commentRef = firebaseDatabase.getReference(COMMENT_KEY).child(key);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listComment = new ArrayList<>();
                for (DataSnapshot snap:snapshot.getChildren()) {

                    Comment comment = snap.getValue(Comment.class);
                    listComment.add(comment);
                }

                commentAdapter = new CommentAdapter(getApplicationContext(),listComment);
                RvComment.setAdapter(commentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    //timestamp
    private String timestampToString(long time) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        return DateFormat.format("dd-MM-yyyy", calendar).toString();
    }
}