package com.ptb.justify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFAQ extends RecyclerView.Adapter<AdapterFAQ.FAQViewHolder> {
    private final ArrayList<FAQClass> arrayList;
    private final Context context;

    public AdapterFAQ(ArrayList<FAQClass> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_faq, parent, false);
        return new FAQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFAQ.FAQViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FAQClass currentFAQ = arrayList.get(position);

        holder.pertanyaanFAQ.setText(arrayList.get(position).pertanyaanFAQ);
        holder.deskripsiFAQ.setText(arrayList.get(position).deskripsiFAQ);

        if (currentFAQ.isVisible) {
            holder.deskripsiFAQ.setVisibility(View.VISIBLE);
            holder.descLineFAQ.setVisibility(View.VISIBLE);
            holder.titleLineFAQ.setVisibility(View.GONE);
            holder.expandMore.setImageResource(R.drawable.expand_less);
        } else {
            holder.deskripsiFAQ.setVisibility(View.GONE);
            holder.descLineFAQ.setVisibility(View.GONE);
            holder.titleLineFAQ.setVisibility(View.GONE);
            holder.expandMore.setImageResource(R.drawable.expand_more);
        }

        holder.card_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayList.get(position).isVisible) {
                    holder.deskripsiFAQ.setVisibility(View.GONE);
                    holder.descLineFAQ.setVisibility(View.GONE);
                    holder.titleLineFAQ.setVisibility(View.GONE);
                    holder.expandMore.setImageResource(R.drawable.expand_more);
                    arrayList.get(position).setVisible(false);
                } else {
                    holder.deskripsiFAQ.setVisibility(View.VISIBLE);
                    holder.descLineFAQ.setVisibility(View.VISIBLE);
                    holder.titleLineFAQ.setVisibility(View.GONE);
                    holder.expandMore.setImageResource(R.drawable.expand_less);
                    arrayList.get(position).setVisible(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class FAQViewHolder extends RecyclerView.ViewHolder {
        CardView card_faq;
        TextView pertanyaanFAQ, deskripsiFAQ;
        RelativeLayout titleLineFAQ, descLineFAQ;
        ImageView expandMore;

        public FAQViewHolder(@NonNull View itemView) {
            super(itemView);
            card_faq = itemView.findViewById(R.id.card_faq);
            pertanyaanFAQ = itemView.findViewById(R.id.pertanyaanFAQ);
            deskripsiFAQ = itemView.findViewById(R.id.deskripsiFAQ);
            titleLineFAQ = itemView.findViewById(R.id.titleLineFAQ);
            descLineFAQ = itemView.findViewById(R.id.descLineFAQ);
            expandMore = itemView.findViewById(R.id.expandMore);
        }
    }
}
