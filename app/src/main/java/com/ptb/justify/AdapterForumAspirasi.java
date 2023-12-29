package com.ptb.justify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterForumAspirasi extends RecyclerView.Adapter<ForumViewHolder> {

    private Context context;
    private List<DataActivity> datalist;

    public void setDatalist(List<DataActivity> datalist) {
        this.datalist = datalist;
    }

    public AdapterForumAspirasi(Context context, List<DataActivity> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    // cara menampilkan ini dengan klik kanan pada datalist;
    // lalu pilih generate -> constructor -> pilih isinya saja, yg atas jangan -> klik OK
    @NonNull
    @Override
    public ForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_forum,parent, false);
        return new ForumViewHolder(view);
    }

    // getGambar, getTitle dkk berasal dari String DataActivity.java
    @Override
    public void onBindViewHolder(@NonNull ForumViewHolder holder, int position) {
        DataActivity dataActivity = datalist.get(position);
        Glide.with(context).load(datalist.get(position).getImageURL()).into(holder.gambar);
        holder.title.setText(dataActivity.getTitle());
        holder.alamat.setText(dataActivity.getAlamat());
        holder.desc.setText(dataActivity.getDesc());
        holder.uid.setText(dataActivity.getUid());


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ForumDetailActivity.class);
                intent.putExtra("gambar", datalist.get(holder.getAdapterPosition()).getImageURL());
                intent.putExtra("title", datalist.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("alamat", datalist.get(holder.getAdapterPosition()).getAlamat());
                intent.putExtra("desc", datalist.get(holder.getAdapterPosition()).getDesc());
                intent.putExtra("Key", datalist.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return datalist.size();
    }

    public void cariDataList(ArrayList<DataActivity> cariList) {
        datalist = cariList;
        notifyDataSetChanged();
    }
}

class ForumViewHolder extends RecyclerView.ViewHolder{

    // inisiasi
    ImageView gambar;
    TextView title, alamat, desc, uid;
    CardView card;

    public ForumViewHolder(@NonNull View itemView) {
        super(itemView);

        card = itemView.findViewById(R.id.recycler_cardForum);
        uid = itemView.findViewById(R.id.recycler_uid);
        gambar = itemView.findViewById(R.id.forum_gambar);
        title = itemView.findViewById(R.id.forum_judul);
        alamat = itemView.findViewById(R.id.forum_domisili);
        desc = itemView.findViewById(R.id.forum_isi);

    }
}
