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

public class AdapterRiwayatBerita extends RecyclerView.Adapter<RViewHolder> {
    private Context context;
    private List<BeritaClass> datalist;

    public void setDatalist(List<BeritaClass> datalist){
        this.datalist = datalist;
    }

    public AdapterRiwayatBerita(Context context, List<BeritaClass> datalist){
        this.context = context;
        this.datalist = datalist;
    }
    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.berita, parent, false);
        return new RViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        BeritaClass beritaClass = datalist.get(position);
        Glide.with(context).load(datalist.get(position).getBeritaImage()).into(holder.gambar);
        holder.title.setText(beritaClass.getBeritaTitle());
        holder.desc.setText(beritaClass.getBeritaDesc());


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailRiwayatBerita.class);
                intent.putExtra("Image", datalist.get(holder.getAdapterPosition()).getBeritaImage());
                intent.putExtra("Description", datalist.get(holder.getAdapterPosition()).getBeritaDesc());
                intent.putExtra("Title", datalist.get(holder.getAdapterPosition()).getBeritaTitle());
                intent.putExtra("Key", datalist.get(holder.getAdapterPosition()).getKey());
                long timestamp = (long) datalist.get(position).getTimestamp();
                intent.putExtra("time", timestamp);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return datalist.size();
    }

    public void cariDataList(ArrayList<BeritaClass> cariList){
        datalist = cariList;
        notifyDataSetChanged();
    }
}

class RViewHolder extends RecyclerView.ViewHolder{
    ImageView gambar;
    TextView title, desc, time;
    CardView card;


    public RViewHolder(@NonNull View itemView) {
        super(itemView);

        gambar = itemView.findViewById(R.id.recImage);
        title = itemView.findViewById(R.id.recTitle);
        card = itemView.findViewById(R.id.card);
        desc = itemView.findViewById(R.id.recDesc);

    }
}
