package com.ptb.justify;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PilihangandaActivity extends AppCompatActivity {

    private int indeksSoalSaatIni;
    private int skor;
    private int[] jawabanBenar = {0, 2, 2, 1, 1, 1, 3, 2, 1, 2};
    private int i;
    Button opsiA, opsiB, opsiC, opsiD, ulangpermainan;
    TextView tekssoal;
    ImageView balik;
    private String[] soal = {
            "1. Istilah korupsi berasal dari bahasa latin \"corruptio\" yang artinya ...",
            "2. Apa yang dimaksud dengan \"tindak pidana korupsi pasif\"?",
            "3. Apa yang dapat menjadi sumber data untuk mengidentifikasi tindakan korupsi?",
            "4. Apa yang dimaksud dengan \"suap\" dalam konteks korupsi?",
            "5. Apa yang dimaksud dengan \"konflik kepentingan\" dalam konteks korupsi?",
            "6. Apa yang dilakukan oleh Lembaga Swadaya Masyarakat (LSM) dalam memerangi korupsi di Indonesia?",
            "7. Lembaga independen yang didirikan untuk memerangi korupsi di Indonesia adalah...",
            "8. Indeks yang mengukur tingkat korupsi di suatu negara, di mana nilai lebih rendah menunjukkan tingkat korupsi yang lebih tinggi, disebut...",
            "9. Aksi penangkapan yang dilakukan oleh penegak hukum ketika menangkap seseorang yang terlibat dalam tindakan korupsi saat sedang berlangsung disebut...",
            "10. Apa yang dimaksud dengan \"whistleblower\" dalam konteks korupsi?",
    };
    private String[][] opsi = {
            {"Tindakan merusak", "Sikap", "Sifat", "Kepribadian"},
            {"Tindakan korupsi yang dilakukan oleh penegak hukum", "Tindakan korupsi yang melibatkan penyuapan pihak ketiga", "Tindakan korupsi yang dilakukan oleh pejabat penerima suap", "Tindakan korupsi yang terjadi di tempat umum"},
            {"Hasil pemilihan umum", "Laporan keuangan pribadi", "Laporan dari whistleblower atau pelapor tindakan korupsi", "Statistik pertumbuhan ekonomi"},
            {"Dana yang digunakan untuk membiayai proyek pemerintah", "Uang yang diberikan dengan tujuan mempengaruhi pejabat", "Dana bantuan dari lembaga internasional", "Dana yang digunakan untuk kampanye politik"},
            {"Situasi di mana pejabat pemerintah hanya melayani kepentingan publik", "Situasi di mana kepentingan pribadi bertentangan dengan kepentingan publik", "Kebijakan yang memprioritaskan kepentingan bisnis", "Situasi di mana semua pihak memiliki kepentingan yang sama"},
            {"Memberikan suap kepada pejabat pemerintah", "Mengaudit proyek-proyek pemerintah", "Mendukung praktik korupsi","Mengorganisir aksi protes"},
            {"Kementrian Negara", "Badan Pemeriksa Keuangan (BPK)", "Kepolisian Negara Republik Indonesia", "Komisi Pemberantasan Korupsi (KPK)"},
            {"Indeks Pembangunan Manusia (IPM)", "Indeks Harga Konsumen (IHK)", "Indeks Persepsi Korupsi (CPI)", "Indeks Pertumbuhan Ekonomi (GDP)"},
            {"Aksi Tertangkap", "Operasi Tangkap Tangan (OTT)", "Operasi Keamanan Publik (OKP)", "Aksi Penyergapan"},
            {"Orang yang mempromosikan praktik korupsi", "Orang yang memberikan suap kepada pejabat pemerintah", "Orang yang melaporkan atau mengungkapkan tindakan korupsi", "Orang yang meminta suap dari pihak lain"},

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihanganda);

        opsiA = findViewById(R.id.opsibutton_A);
        opsiB = findViewById(R.id.opsibutton_B);
        opsiC = findViewById(R.id.opsibutton_C);
        opsiD = findViewById(R.id.opsibutton_D);
        ulangpermainan = findViewById(R.id.ulang_permainan);
        tekssoal = findViewById(R.id.teks_soal);
        balik = findViewById(R.id.imageLeft);

        balik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(PilihangandaActivity.this, "Haloo!", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
            }
        });

        tampilPertanyaan();
        ulangpermainan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartQuiz();
                tampilPertanyaan();
            }
        });

        opsiA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { cekJawaban(0);
            }
        });
        opsiB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekJawaban(1);
            }
        });
        opsiC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekJawaban(2);
            }
        });
        opsiD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { cekJawaban(3);
            }
        });
    }

    private void cekJawaban(int i) {
        int jawabanBenarIndeks = jawabanBenar[indeksSoalSaatIni];

        if (i == jawabanBenarIndeks) {
            skor++;
            warnaButtonBenar(i);
        } else {
            warnaButtonSalah(i);
            warnaButtonBenar(jawabanBenarIndeks);
        }

        if (indeksSoalSaatIni < soal.length - 1) {
            indeksSoalSaatIni++;
            tekssoal.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tampilPertanyaan();
                }
            }, 1000);
        } else {
            tampilNilai();
        }

    }

    private void warnaButtonSalah(int buttonIndex) {
        switch (buttonIndex) {
            case 0:
                opsiA.setBackgroundColor(Color.parseColor("#E9BDBD"));
                break;
            case 1:
                opsiB.setBackgroundColor(Color.parseColor("#E9BDBD"));
                break;
            case 2:
                opsiC.setBackgroundColor(Color.parseColor("#E9BDBD"));
                break;
            case 3:
                opsiD.setBackgroundColor(Color.parseColor("#E9BDBD"));
                break;
        }
    }

    private void warnaButtonBenar(int buttonIndex) {
        switch (buttonIndex) {
            case 0:
                opsiA.setBackgroundColor(Color.parseColor("#D1DAE0"));
                break;
            case 1:
                opsiB.setBackgroundColor(Color.parseColor("#D1DAE0"));
                break;
            case 2:
                opsiC.setBackgroundColor(Color.parseColor("#D1DAE0"));
                break;
            case 3:
                opsiD.setBackgroundColor(Color.parseColor("#D1DAE0"));
                break;
        }
    }

    private void tampilPertanyaan() {
        tekssoal.setText(soal[indeksSoalSaatIni]);
        opsiA.setText(opsi[indeksSoalSaatIni][0]);
        opsiB.setText(opsi[indeksSoalSaatIni][1]);
        opsiC.setText(opsi[indeksSoalSaatIni][2]);
        opsiD.setText(opsi[indeksSoalSaatIni][3]);
        warnaResetButton();
    }
    private void warnaResetButton() {
        opsiA.setBackgroundColor(Color.rgb(250, 250, 250));
        opsiB.setBackgroundColor(Color.rgb(250, 250, 250));
        opsiC.setBackgroundColor(Color.rgb(250, 250, 250));
        opsiD.setBackgroundColor(Color.rgb(250, 250, 250));
    }

    private void tampilNilai() {
        Toast.makeText(this, "Nilai Anda: " + skor + " dari " + soal.length, Toast.LENGTH_SHORT).show();
        ulangpermainan.setEnabled(true);
    }

    private void restartQuiz() {
        indeksSoalSaatIni = 0;
        skor = 0;
        ulangpermainan.setEnabled(false);
        opsiA.setEnabled(true);
        opsiB.setEnabled(true);
        opsiC.setEnabled(true);
        opsiD.setEnabled(true);
        tampilPertanyaan();

    }


}
