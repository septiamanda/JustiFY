package com.ptb.justify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class FaqActivity extends AppCompatActivity {

    ArrayList<FAQClass> arrayListFAQ;
    RecyclerView recyclerViewFAQ;
    AdapterFAQ adapterFAQ;
    ImageView close, expandMore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        recyclerViewFAQ = findViewById(R.id.rv_faq);
        close = findViewById(R.id.closeFAQ);


        String misiText = "1. Menyediakan informasi yang akurat, bermanfaat, dan mudah dipahami tentang korupsi, serta memberikan alat edukasi yang interaktif." +
                "\n\n2. Memberdayakan pengguna untuk menyampaikan aspirasi mereka dan berpartisipasi dalam forum untuk berbagi pemikiran, pengalaman, dan solusi." +
                "\n\n3. Menyajikan berita, artikel, dan pembaruan undang-undang terkait korupsi agar pengguna tetap terinformasi tentang perkembangan terbaru.";
        arrayListFAQ = new ArrayList<>();

        arrayListFAQ.add(new FAQClass("Apa itu aplikasi JustiFY?","JustiFY adalah aplikasi inovatif " +
                "yang didedikasikan untuk memberikan edukasi anti korupsi dan memotivasi partisipasi aktif " +
                "masyarakat dalam perang melawan praktik korupsi yang merugikan.", false));
        arrayListFAQ.add(new FAQClass("Visi","Mewujudkan masyarakat yang sadar, berpendidikan, " +
                "dan proaktif dalam memerangi korupsi untuk menciptakan lingkungan yang adil dan bermoral.", false));
        arrayListFAQ.add(new FAQClass("Misi", misiText, false));
        arrayListFAQ.add(new FAQClass("Nilai dan Prinsip","Kami memegang teguh nilai-nilai " +
                "transparansi, integritas, dan partisipasi aktif dalam semua aspek pengembangan dan pengelolaan JustiFY.", false));
        arrayListFAQ.add(new FAQClass("Panduan Pengguna","Agar Anda dapat memaksimalkan pengalaman menggunakan JustiFY, " +
                "kami menyediakan panduan pengguna yang ringkas. Temukan petunjuk langkah demi langkah untuk fitur-fitur kami di bagian FAQ.", false));
        arrayListFAQ.add(new FAQClass("Kontak dan Dukungan","Butuh bantuan atau punya pertanyaan? " +
                "Jangan ragu untuk menghubungi tim dukungan kami melalui email di support@justifyapp.com.", false));

        adapterFAQ = new AdapterFAQ(arrayListFAQ, FaqActivity.this);
        recyclerViewFAQ.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFAQ.setAdapter(adapterFAQ);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FaqActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
