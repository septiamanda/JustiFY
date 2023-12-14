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

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<BeritaClass> dataList;

    public void setDataList(List<BeritaClass> datalist){
        this.dataList = datalist;
    }

    public MyAdapter(Context context, List<BeritaClass> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.berita, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getBeritaImage()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getBeritaTitle());
        holder.recDesc.setText(dataList.get(position).getBeritaDesc());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailBerita.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getBeritaImage());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getBeritaDesc());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getBeritaTitle());
                intent.putExtra("Key", dataList.get(holder.getAdapterPosition()).getKey());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<BeritaClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recTitle,recDesc;
    CardView recCard;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recTitle = itemView.findViewById(R.id.recTitle);
        recCard = itemView.findViewById(R.id.card);
        recDesc = itemView.findViewById(R.id.recDesc);
    }
}
