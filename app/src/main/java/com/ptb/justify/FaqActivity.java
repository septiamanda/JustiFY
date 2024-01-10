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

        arrayListFAQ = new ArrayList<>();

        arrayListFAQ.add(new FAQClass("Apa itu aplikasi JustiFY?","JustiFY adalah sebuah aplikasi mobile yang didesain untuk memberikan edukasi anti korupsi. Aplikasi ini menyajikan berbagai fitur, seperti berita, artikel, forum aspirasi, dan permainan pilihan ganda untuk meningkatkan kesadaran masyarakat mengenai korupsi.\n", false));
        arrayListFAQ.add(new FAQClass("Apa yang membedakan JustiFY dari aplikasi edukasi lainnya?\n","JustiFY memberikan fokus khusus pada edukasi anti korupsi dengan menyajikan konten yang informatif dan bermanfaat, termasuk berita terkini, artikel, dan forum aspirasi untuk berbagi pemikiran dan pengalaman terkait korupsi.\n", false));
        arrayListFAQ.add(new FAQClass("Bagaimana cara menggunakan fitur game pilihan ganda?\n","Langsung klik Game di menu utama dan akan menampilkan game pilihan ganda. Game pilihan ganda dirancang untuk menguji pengetahuan pengguna tentang korupsi secara interaktif. Pengguna dapat menjawab pertanyaan-pertanyaan seputar korupsi dan mendapatkan poin serta pemahaman yang lebih baik.\n", false));
        arrayListFAQ.add(new FAQClass("Apa manfaat dari forum aspirasi di JustiFY?\n","Forum aspirasi memungkinkan pengguna berinteraksi, berdiskusi, dan berbagi aspirasi terkait korupsi. Hal ini memungkinkan masyarakat untuk saling mendukung dan menyuarakan pandangan mereka.\n", false));
        arrayListFAQ.add(new FAQClass("Bagaimana cara membuat aspirasi di aplikasi ini?\n","Untuk membuat aspirasi, masuklah ke bagian \"Aspirasi\" lalu pilih ikon \"Tambah \" Isilah formulir dengan judul, isi aspirasi, dan informasi lain yang diperlukan, lalu kirim untuk berbagi pandangan Anda tentang korupsi.\n", false));

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
