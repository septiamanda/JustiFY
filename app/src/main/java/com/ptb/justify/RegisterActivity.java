package com.ptb.justify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText registeremail, registerusername, registerkatasandi, registertelepon, registerconf;
    private Button buttondaftar;
    private TextView registermasuk;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

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

        buttondaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = registerusername.getText().toString().trim();
                String user = registeremail.getText().toString().trim();
                String pass = registerkatasandi.getText().toString().trim();
                String konfirmasi = registerconf.getText().toString().trim();

                if (user.isEmpty()) {
                    registeremail.setError("Email tidak boleh kosong");
                }
                if (pass.isEmpty()) {
                    registerkatasandi.setError("Kata sandi tidak boleh kosong");
                } else if(!pass.equals(konfirmasi)){
                    Toast.makeText(RegisterActivity.this, "Password tidak sesuai", Toast.LENGTH_SHORT).show();
                } else {

                    databaseReference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()){
                                if(task.getResult().exists()){
                                    Toast.makeText(RegisterActivity.this, "username sudah ada", Toast.LENGTH_SHORT).show();
                                } else {
                                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {

                                                DataUser user = new DataUser(
                                                        registeremail.getText().toString().trim(),
                                                        registertelepon.getText().toString().trim(),
                                                        registerusername.getText().toString().trim()
                                                );
                                                databaseReference.child(username).setValue(user);

                                                Toast.makeText(RegisterActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                            } else {
                                                Toast.makeText(RegisterActivity.this, "Register Gagal" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });

                }
            }
        });
    }
}