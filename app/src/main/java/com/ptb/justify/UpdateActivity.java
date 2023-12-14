package com.ptb.justify;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

import java.security.Key;

public class UpdateActivity extends AppCompatActivity {

    ImageView updateImage, updateKembali;
    Button updateButton;
    EditText updateDesc, updateTitle, updateAlamat;
    String title, desc, alamat;
    String imageUrl;
    String key, oldImageUrl;
    Uri uri;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateButton = findViewById(R.id.update_kirim);
        updateDesc = findViewById(R.id.update_isi);
        updateImage = findViewById(R.id.update_gambar);
        updateAlamat = findViewById(R.id.update_domisili);
        updateTitle = findViewById(R.id.update_judul);
        updateKembali = findViewById(R.id.update_kembali);

        updateKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(getApplicationContext(), AspirasiDetailActivity.class);
                startActivity(detail);
                //disini kasi penanganan agar datanya dapat tersimpan, disini belum manda kasi penanganan, jdinya pas klik kembali di aplikasi datanya gaada
            }
        });

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                uri = data.getData();
                                if (uri != null) {
                                    updateImage.setImageURI(uri);
                                }
                            }
                        } else {
                            Toast.makeText(UpdateActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String imageUrl = bundle.getString("Image");
            if (imageUrl != null) {
                Glide.with(UpdateActivity.this).load(imageUrl).into(updateImage);
            }
            updateTitle.setText(bundle.getString("Title"));
            updateDesc.setText(bundle.getString("Desc"));
            updateAlamat.setText(bundle.getString("Alamat"));
            key = bundle.getString("Key");
            oldImageUrl = imageUrl;
        }

        if (key != null) {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            String uid = auth.getCurrentUser().getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorial").child(uid).child(key);
        }

        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(UpdateActivity.this, AspirasiActivity.class);
                startActivity(intent);
            }
        });
    }

    public void saveData() {
        if (uri != null) {
            storageReference = FirebaseStorage.getInstance().getReference().child("Android Images").child(uri.getLastPathSegment());

            AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
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
                    imageUrl = urlImage.toString();
                    updateData();
                    dialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                }
            });
        }
    }

    public void updateData() {
        title = updateTitle.getText().toString().trim();
        alamat = updateAlamat.getText().toString();
        desc = updateDesc.getText().toString().trim();

        if (key != null) {
            DataActivity dataActivity = new DataActivity(imageUrl, title, alamat, desc);

            databaseReference.setValue(dataActivity).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        if (oldImageUrl != null) {
                            StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageUrl);
                            reference.delete();
                        }
                        Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
