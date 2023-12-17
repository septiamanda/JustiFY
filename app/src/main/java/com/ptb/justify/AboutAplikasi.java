package com.ptb.justify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class AboutAplikasi extends AppCompatActivity {

    ArrayList<AboutClass> arrayList;
    RecyclerView recyclerView;
    AdapterAbout adapter;
    ImageView close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_aplikasi);
        recyclerView = findViewById(R.id.rv_about);
        close = findViewById(R.id.close4);
        String misiText = "1. Menyediakan informasi yang akurat, bermanfaat, dan mudah dipahami tentang korupsi, serta memberikan alat edukasi yang interaktif." +
                "\n\n2. Memberdayakan pengguna untuk menyampaikan aspirasi mereka dan berpartisipasi dalam forum untuk berbagi pemikiran, pengalaman, dan solusi." +
                "\n\n3. Menyajikan berita, artikel, dan pembaruan undang-undang terkait korupsi agar pengguna tetap terinformasi tentang perkembangan terbaru.";
        arrayList = new ArrayList<>();

        arrayList.add(new AboutClass("Deskripsi Aplikasi","JustiFY adalah aplikasi inovatif " +
                "yang didedikasikan untuk memberikan edukasi anti korupsi dan memotivasi partisipasi aktif " +
                "masyarakat dalam perang melawan praktik korupsi yang merugikan.", false));
        arrayList.add(new AboutClass("Visi","Mewujudkan masyarakat yang sadar, berpendidikan, " +
                "dan proaktif dalam memerangi korupsi untuk menciptakan lingkungan yang adil dan bermoral.", false));
        arrayList.add(new AboutClass("Misi",misiText, false));
        arrayList.add(new AboutClass("Nilai dan Prinsip","Kami memegang teguh nilai-nilai " +
                "transparansi, integritas, dan partisipasi aktif dalam semua aspek pengembangan dan pengelolaan JustiFY.", false));
        arrayList.add(new AboutClass("Panduan Pengguna","Agar Anda dapat memaksimalkan pengalaman menggunakan JustiFY, " +
                "kami menyediakan panduan pengguna yang ringkas. Temukan petunjuk langkah demi langkah untuk fitur-fitur kami di bagian FAQ.", false));
        arrayList.add(new AboutClass("Kontak dan Dukungan","Butuh bantuan atau punya pertanyaan? " +
                "Jangan ragu untuk menghubungi tim dukungan kami melalui email di support@justifyapp.com.", false));




        adapter = new AdapterAbout(arrayList,AboutAplikasi.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutAplikasi.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}