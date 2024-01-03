package com.ptb.justify;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.security.Permission;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText registeremail, registerusername, registerkatasandi, registertelepon, registerconf;
    private Button buttondaftar;
    private TextView registermasuk;
    private ImageView userphoto;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    Uri pickedImgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registeremail = findViewById(R.id.register_email);
        registerkatasandi = findViewById(R.id.register_katasandi);
        buttondaftar = findViewById(R.id.register_button);
        registermasuk = findViewById(R.id.register_masuk);
        registertelepon = findViewById(R.id.register_telepon);
        registerusername = findViewById(R.id.register_username);
        registerconf = findViewById(R.id.register_konfirmasi);


        registermasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        auth = FirebaseAuth.getInstance();

        buttondaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = registerusername.getText().toString();
                final String notelepon = registertelepon.getText().toString();
                final String user = registeremail.getText().toString();
                final String pass = registerkatasandi.getText().toString();
                final String konfirmasi = registerconf.getText().toString();

                if (user.isEmpty()) {
                    registeremail.setError("Email tidak boleh kosong");
                }
                if (pass.isEmpty()) {
                    registerkatasandi.setError("Kata sandi tidak boleh kosong");
                } else if(!pass.equals(konfirmasi)){
                    Toast.makeText(RegisterActivity.this, "Password tidak sesuai", Toast.LENGTH_SHORT).show();
                } else {
                    CreateUserAccount(user, notelepon, username, pass);
                }
            }
        });

        userphoto = findViewById(R.id.usergambar);

        userphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private void CreateUserAccount(String user, String notelepon, String username, String pass) {
        auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    showMessage("akun terbuat");
                    updateUserInfo(username, pickedImgUri, auth.getCurrentUser());
                }
                else{
                    showMessage("account creation failed" + task.getException().getMessage());
                }
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    private void updateUserInfo(final String username, Uri pickedImgUri, FirebaseUser currentUser) {

        // first we need to upload user photo to firebase storage and get url

        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // image uploaded succesfully
                // now we can get our image url

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // uri contain user image url


                        UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(username)
                                .setPhotoUri(uri)
                                .build();


                        currentUser.updateProfile(profleUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    showMessage("Register Complete");
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void openGallery() {
        //TODO: open gallery intent and wait for user to pick an image !

        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        activityResultLauncher.launch(galleryIntent);
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        pickedImgUri = data.getData();
                        userphoto.setImageURI(pickedImgUri);
                    } else {
                        Toast.makeText(RegisterActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
}