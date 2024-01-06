package com.ptb.justify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class TentangKorupsi extends AppCompatActivity {

    ArrayList<KorupsiActivity> arrayList;
    RecyclerView recyclerView;
    AdapterKorupsi adapter;
    ImageView kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_korupsi);
        recyclerView = findViewById(R.id.recycler_korupsi);
        kembali = findViewById(R.id.close4);

        String ciri = "1. Selalu melibatkan lebih dari satu orang." +
                "\n\n2. Biasanya dilakukan dengan kerahasiaan." +
                "\n\n3. Melibatkan pihak yang saling menguntungkan dan menjaga kewajiban." +
                "\n\n4. Melibatkan pihak yang saling menguntungkan dan menjaga kewajiban." +
                "\n\n5. Oknumnya sering berasal dari pihak yang berkepentingan." +
                "\n\n6. Korupsi adalah penipuan bagi badan publik dan masyarakat umum secara keseluruhan." +
                "\n\n7. Oknum yang melakukan korupsi sering bersembunyi di balik justifikasi hukum" ;

        String jenis = "1. Penyuapan (Bribery)\n" +
                "Penyuapan adalah pembayaran Salam bentuk uang atau sejenisnya yang diberikan atau diambil dalam hubungan korupsi." +
                "\n\n2. Penggelapan/Pencurian (Embezzlement)\n" +
                "Penggelapan atau pencurian merupakan tindakan kejahatan menggelapkan atau mencuri uang rakyat yang dilakukan oleh pegawai pemerintah, pegawai sektor swasta, atau aparat birokrasi." +
                "\n\n3. Penipuan (Fraud)\n" +
                "Penipuan atau fraud merupakan kejahatan ekonomi berwujud kebohongan, penipuan, dan perilaku tidak jujur. Jenis korupsi ini merupakan kejahatan ekonomi yang terorganisir dan biasanya melibatkan pejabat." +
                "\n\n4. Pemerasan (Extortion)\n" +
                "Korupsi dalam bentuk pemerasan merupakan jenis korupsi yang melibatkan aparat dengan melakukan pemaksaan untuk mendapatkan keuntungan sebagai imbal jasa pelayanan yang diberikan." +
                "\n\n5. Favoritisme (Favortism)\n" +
                "Favoritisme dikenal juga dengan pilih kasih merupakan tindak penyalahgunaan kekuasaan yang melibatkan tindak privatisasi sumber daya.";

        String faktor = "1. Pressure (tekanan)\n" +
                "Memiliki motivasi untuk melakukan tindakan korupsi karena adanya tekanan, salah satunya karena motif ekonomi. " +
                "\n\n2. Opportunity (kesempatan)\n" +
                "Adanya kesempatan membuat seseorang tergiur untuk korupsi. Ini terjadi akibat dari lemahnya sistem pengawasan yang pada akhirnya menjerumuskan pelaku melakukan korupsi." +
                "\n\n3. Rationalization (rasionalisasi)\n" +
                "Para pelaku selalu memiliki rasionalisasi atau pembenaran untuk melakukan korupsi. Rasionalisasi ini ternyata dapat menipiskan rasa bersalah yang dimiliki pelaku dan merasa dirinya tidak mendapatkan keadilan. " ;

        String bahaya = "1. Bahaya Korupsi terhadap Masyarakat dan Individu\n" +
                "Jika korupsi dalam suatu masyarakat telah merajalela dan menjadi makanan masyarakat setiap\n" +
                "hari, maka akibatnya akan menjadikan masyarakat tersebut sebagai masyarakat yang kacau, tidak\n" +
                "ada sistem sosial yang dapat berlaku dengan baik. Setiap individu dalam masyarakat hanya akan\n" +
                "mementingkan diri sendiri (self interest), bahkan selfishness.\n" +
                "Tidak akan ada kerja sama dan persaudaraan yang tulus. " +
                "\n\n2. Bahaya Korupsi terhadap Generasi Muda\n" +
                "Salah satu efek negatif yang paling berbahaya dari korupsi pada jangka panjang adalah rusaknya generasi muda. D" +
                "alam masyarakat yang korupsi telah menjadi makanan sehari-hari, anak tumbuh dengan pribadi antisosial, selanjutnya g" +
                "enerasi muda akan menganggap bahwa korupsi sebagai hal biasa (atau bahkan budaya), sehingga perkembangan pribadinya menjadi terbiasa dengan sifat tidak jujur dan tidak bertanggung jawab." +
                "\n\n3. Bahaya Korupsi terhadap Politik\n" +
                "Kekuasaan politik yang dicapai dengan korupsi akan menghasilkan pemerintahan dan pemimpin masyarakat yang tidak legitimate di mata publik. " +
                "Jika demikian keadaannya, maka masyarakat tidak akan percaya terhadap pemerintah dan pemimpin tersebut, akibatnya mereka tidak akan patuh dan tunduk pada otoritas mereka. " +
                "\n\n4. Bahaya Korupsi Bagi Ekonomi Bangsa\n" +
                "Korupsi merusak perkembangan ekonomi suatu bangsa.Jika suatu projek ekonomi dijalankan sarat dengan unsur-unsur korupsi (penyuapan untuk kelulusan projek, nepotisme dalam " +
                "penunjukan pelaksana projek, penggelepan dalam pelaksanaannya dan lain-lain bentuk korupsi dalam projek), maka pertumbuhan ekonomi yang diharapkan dari projek tersebut tidak akan tercapai.\n " +
                "\n\n5. Bahaya Korupsi Bagi Birokrasi\n" +
                "Korupsi juga menyebabkan tidak efisiennya birokrasi dan meningkatnya biaya administrasi dalam birokrasi. Jika birokrasi telah dikungkungi oleh korupsi dengan berbagai bentuknya, " +
                "maka prinsip dasar birokrasi yang rasional, efisien, dan berkualitas akan tidak pernah terlaksana.\n" ;


        arrayList = new ArrayList<>();
        arrayList.add(new KorupsiActivity("Apa itu Korupsi?","Korupsi adalah suatu bentuk ketidakjujuran atau tindak pidana yang dilakukan" +
                "oleh seseorang atau suatu organisasi yang dipercayakan dalam suatu jabatan kekuasaan, untuk memperoleh keuntungan yang haram atau " +
                "penyalahgunaan kekuasaan untuk keuntungan pribadi seseorang.", false));
        arrayList.add(new KorupsiActivity("Ciri-Ciri Korupsi",ciri, false));
        arrayList.add(new KorupsiActivity("Jenis-Jenis Korupsi",jenis, false));
        arrayList.add(new KorupsiActivity("Faktor Pendorong Korupsi",faktor, false));
        arrayList.add(new KorupsiActivity("Bahaya Korupsi",bahaya, false));


        adapter = new AdapterKorupsi(arrayList,TentangKorupsi.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TentangKorupsi.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}