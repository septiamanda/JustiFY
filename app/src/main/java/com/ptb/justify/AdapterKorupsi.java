package com.ptb.justify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterKorupsi extends RecyclerView.Adapter<AdapterKorupsi.KorupsiViewHolder>{

    ArrayList<KorupsiActivity> arrayList;
    Context context;

    public AdapterKorupsi(ArrayList<KorupsiActivity> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public AdapterKorupsi.KorupsiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_about, null, false);
        return new AdapterKorupsi.KorupsiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKorupsi.KorupsiViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_title.setText(arrayList.get(position).title);
        holder.tv_desc.setText(arrayList.get(position).description);

        holder.card_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrayList.get(position).isVisible){
                    holder.tv_desc.setVisibility(View.GONE);
                    holder.desc_line.setVisibility(View.GONE);
                    holder.title_line.setVisibility(View.VISIBLE);
                    arrayList.get(position).isVisible = false;
                }
                else{
                    holder.tv_desc.setVisibility(View.VISIBLE);
                    holder.desc_line.setVisibility(View.VISIBLE);
                    holder.title_line.setVisibility(View.GONE);
                    arrayList.get(position).isVisible = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class KorupsiViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title, tv_desc;
        RelativeLayout title_line, desc_line;
        CardView card_about;
        public KorupsiViewHolder(@NonNull View itemView){
            super(itemView);
            tv_title = itemView.findViewById(R.id.titleabt);
            tv_desc = itemView.findViewById(R.id.descabt);
            title_line = itemView.findViewById(R.id.title_line);
            desc_line = itemView.findViewById(R.id.desc_line);
            card_about = itemView.findViewById(R.id.card_about);
        }
    }
}
