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

public class AdapterArtikel extends RecyclerView.Adapter<AViewHolder> {
    private Context context;
    private List<ArtikelClass> dataList;

    public AdapterArtikel(Context context, List<ArtikelClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    public void setDataList(List<ArtikelClass> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_artikel, parent, false);
        return new AViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getArtikelImage()).into(holder.gambar);
        holder.judul.setText(dataList.get(position).getArtikelJudul());
        holder.isi.setText(dataList.get(position).getArtikelDeskripsi());
        holder.uname.setText(dataList.get(position).getUsername());
        holder.time.setText(dataList.get(position).getFormatedDate());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailArtikel.class);
                intent.putExtra("Gambar", dataList.get(holder.getAdapterPosition()).getArtikelImage());
                intent.putExtra("Judul", dataList.get(holder.getAdapterPosition()).getArtikelJudul());
                intent.putExtra("Isi", dataList.get(holder.getAdapterPosition()).getArtikelDeskripsi());
                intent.putExtra("Uname", dataList.get(holder.getAdapterPosition()).getUsername());
                long timestamp = (long) dataList.get(position).getTimestamp();
                intent.putExtra("time", timestamp);
                intent.putExtra("Key", dataList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<ArtikelClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class AViewHolder extends RecyclerView.ViewHolder{
    ImageView gambar;
    TextView judul, isi, uname, time;
    CardView card;

    public AViewHolder(@NonNull View itemView) {
        super(itemView);

        gambar = itemView.findViewById(R.id.gambarArtikel);
        judul = itemView.findViewById(R.id.judulArtikel);
        isi = itemView.findViewById(R.id.isiArtikel);
        card = itemView.findViewById(R.id.card_artikel);
        uname = itemView.findViewById(R.id.username);
        time = itemView.findViewById(R.id.time);

    }
}
