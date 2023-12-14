package com.ptb.justify;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Calendar;

public class TambahAspirasiActivity extends AppCompatActivity {

    ImageView gambar, kembali;
    EditText judul, domisili, isi;
    Button kirim;
    long timestamp;
    String imageURL;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_aspirasi);

        gambar = findViewById(R.id.aspirasi_gambar);
        judul = findViewById(R.id.aspirasi_judul);
        domisili = findViewById(R.id.aspirasi_domisili);
        isi = findViewById(R.id.aspirasi_isi);
        kirim = findViewById(R.id.aspirasi_kirim);
        kembali = findViewById(R.id.tambah_kembali);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aspirasi = new Intent(getApplicationContext(), AspirasiActivity.class);
                startActivity(aspirasi);
            }
        });

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            gambar.setImageURI(uri);
                        } else {
                            Toast.makeText(TambahAspirasiActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    public void saveData() {

        String title = judul.getText().toString();
        String alamat = domisili.getText().toString();
        String desc = isi.getText().toString();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images").child(uid)
                .child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(TambahAspirasiActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.activity_progres);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadData(title, alamat, desc, uid);
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                dialog.dismiss();
            }
        });
    }
    // string berasal dari DataActivity.java
    public void uploadData(String title, String alamat, String desc, String uid) {

        DataActivity dataActivity = new DataActivity(imageURL, title, alamat, desc);
        // menambahkan waktu
        dataActivity.setTimestamp();

        //mengganti child dari tittle menjadi currentDate,
        //karena kita akan mengupdate title as well and it may affect child value

        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        String sanitizedDate = currentDate.replaceAll("[^a-zA-Z0-9]", "");

        FirebaseDatabase.getInstance().getReference("Android Tutorial").child(uid).child(sanitizedDate)
                .setValue(dataActivity).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(TambahAspirasiActivity.this, "Simpan", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TambahAspirasiActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}