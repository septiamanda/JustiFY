package com.ptb.justify;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class FirebaseDemo extends AppCompatActivity {

    final String TAG = "tcctag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(Task<String> task) {
                if (!task.isSuccessful()) {
                    // Jika gagal mendapatkan token
                    Exception exception = task.getException();
                    if (exception != null) {
                        // Tambahkan penanganan kesalahan di sini
                        exception.printStackTrace();
                    }
                    return;
                }

                // Dapatkan token Firebase Cloud Messaging (FCM) yang baru
                String token = task.getResult();
                Log.d("FCM Token", token);
            }
        });
    }
}