package com.ptb.justify;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class TambahArtikel extends AppCompatActivity {
    ImageView back, gambar;
    Button simpan;
    EditText judulArtkl, isiArtkl;
    long timestamp;
    String imageURL;
    Uri uri;
    DatabaseReference databaseReference = FirebaseDatabase. getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_artikel);

        back = findViewById(R.id.balikArtikel);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahArtikel.this, ArtikelActivity.class);
                startActivity(intent);
            }
        });

        gambar = findViewById(R.id.imageArtikel);
        judulArtkl = findViewById(R.id.judulArtikel);
        isiArtkl = findViewById(R.id.isiArtikel);
        simpan = findViewById(R.id.simpanArtikel);


        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            gambar.setImageURI(uri);
                        } else {
                            Toast.makeText(TambahArtikel.this, "Tidak ada gambar yang dipilih", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timestamp = System.currentTimeMillis();
                saveData();
            }
        });

    }

    private void saveData() {
        String judul = judulArtkl.getText().toString();
        String isi = isiArtkl.getText().toString();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String uname = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        String uphoto = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images").child(uid)
                .child(uri.getLastPathSegment());

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(TambahArtikel.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadData(judul, isi, uid, uname, uphoto);
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    private void uploadData(String judul, String isi, String uid, String uname, String uphoto) {
        ArtikelClass artikelClass = new ArtikelClass(judul, isi, imageURL, uid, uname, uphoto);
        artikelClass.setTimestamp(timestamp);
        artikelClass.setUid(uid);
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        String sanitizedDate = currentDate.replaceAll("[^a-zA-Z0-9]", "");

        FirebaseDatabase.getInstance().getReference("Android Artikel").child(uid).child(sanitizedDate)
                .setValue(artikelClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            showNotification("Data berhasil ditambahkan!", "Notifikasi");
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TambahArtikel.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
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
                    .setSmallIcon(R.mipmap.ic_launcher)
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
}

