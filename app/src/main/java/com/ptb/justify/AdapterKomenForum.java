//package com.ptb.justify;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//
//import org.w3c.dom.Comment;
//
//import java.util.List;
//
//public class AdapterKomenForum extends RecyclerView.Adapter<KomenViewHolder> {
//
//    private Context context;
//    private List<KomenActivity> commentlist;
//
//    public AdapterKomenForum(Context context, List<KomenActivity> commentlist) {
//        this.context = context;
//        this.commentlist = commentlist;
//    }
//
//    @NonNull
//    @Override
//    public KomenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_komen,parent, false);
//        return new KomenViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull KomenViewHolder holder, int position) {
//        KomenActivity komenActivity = commentlist.get(position);
//        Glide.with(context).load(commentlist.get(position).getUphoto()).into(holder.Kgambar);
//        holder.Kusername.setText(komenActivity.getUname());
//        holder.Kdesc.setText(komenActivity.getUdesc());
//        holder.Ktime.setText(komenActivity.getFormattedDate());
//    }
//
//    @Override
//    public int getItemCount() {
//
//        return commentlist.size();
//    }
//}
//
//class KomenViewHolder extends RecyclerView.ViewHolder{
//    ImageView Kgambar;
//    TextView Kusername, Ktime, Kdesc;
//    public KomenViewHolder(@NonNull View itemView) {
//        super(itemView);
//
//        Kgambar = itemView.findViewById(R.id.recycler_gambar_komen);
//        Kusername = itemView.findViewById(R.id.recycler_username_komen);
//        Kdesc = itemView.findViewById(R.id.recycler_isi_komen);
//        Ktime = itemView.findViewById(R.id.recycler_time_komen);
//    }
//}
//
