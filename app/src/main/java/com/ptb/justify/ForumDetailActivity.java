package com.ptb.justify;

import static okhttp3.Request.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.jar.JarException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

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


                // Device Samsung to Xiomi
                commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        String uname = detailUsernameF.getText().toString().trim();
                        String comment_content = detailKomen.getText().toString().trim();
                        if (!uname.equals("") && !comment_content.equals("")) {
                            sendNotification(
                                    ForumDetailActivity.this,
                                    "ewjdygHcSP-WYQKUCTuZTK:APA91bEhxCVwgu5t2U4QmDcYQK7pYIAk1k__wS2AJtrOGfNeeJ8Xvn_h4kVb6sX3i5fBWTDR3U2HssV-QIYYznRqCNXBv7g2E0NWx0Kdz3qvOsL5OK4BEdKxLBTucD13Xcj4Y6WYoDqf",
                                    uname,
                                    comment_content
                            );
                        }
                        showMessage("comment added");
                        showNotification("Komen Berhasil Dikirim", "Forum Aspirasi");
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


    private static String BASE_URL = "https://fcm.googleapis.com/fcm/send";
    private static String SERVER_KEY = "key=BPSxpVVFeYgm9XFyldGUdYLbxz52sj1R-rUozL6MZ0bKuryahs92S6ZE0n3J8ZLyNHjfkKmFKt7_fIGvkd-We30";

    private static void sendNotification(Context context, String token, String title, String message) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(context);

        try {
            JSONObject json = new JSONObject();
            json.put("to", token);
            JSONObject notification = new JSONObject();
            notification.put("title", title);
            notification.put("body", message);
            json.put("notification", notification);

            // Buat permintaan JSON
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL, json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("FCM" + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Tanggapi kesalahan pengiriman notifikasi (jika diperlukan)
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", "key=" + SERVER_KEY); // Perhatikan perubahan ini
                    return params;
                }
            };

            // Tambahkan permintaan ke antrian
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showNotification(String message, String title) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "JF";
        String channelName = "JustiFy";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 100});
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, channelId)
                    .setSmallIcon(R.drawable.logo1)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(channelId);
        }

        Notification notification = builder.build();
        notificationManager.notify(0, notification);
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