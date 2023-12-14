package com.ptb.justify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterUU extends RecyclerView.Adapter<AdapterUU.UUViewHolder> {
    private Context context;
    private List<UUClass> dataList;

    public AdapterUU(Context context, List<UUClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public UUViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_uu, parent, false);
        return new UUViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UUViewHolder holder, int position) {
        holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recDesc.setText(dataList.get(position).getDataDesc());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailUU.class);
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Desc", dataList.get(holder.getAdapterPosition()).getDataDesc());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class UUViewHolder extends RecyclerView.ViewHolder {
        TextView recTitle, recDesc;
        CardView recCard;

        public UUViewHolder(@NonNull View itemView) {
            super(itemView);
            recTitle = itemView.findViewById(R.id.tv_judul);
            recDesc = itemView.findViewById(R.id.tv_isi);
            recCard = itemView.findViewById(R.id.card_uu);
        }
    }
}
