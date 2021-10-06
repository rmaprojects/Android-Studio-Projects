package project.raka.m.a.portalberitaext.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import project.raka.m.a.portalberitaext.R;
import project.raka.m.a.portalberitaext.model.ArticlesItem;
import project.raka.m.a.portalberitaext.view.DetailActivity;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.MyViewHolder>{

    Context context;
    List<ArticlesItem> articlesItems;

    public AdapterBerita(Context context, List<ArticlesItem> articlesItems){
        this.context = context;
        this.articlesItems = articlesItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listberita, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txJudul.setText(articlesItems.get(position).getTitle());
        holder.txSumberBerita.setText(articlesItems.get(position).getSource().getName());
        String LINK_IMAGE = articlesItems.get(position).getUrlToImage();
        Picasso.get().load(LINK_IMAGE).into(holder.ivPictureBerita);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putString("JUDUL", articlesItems.get(position).getTitle());
                data.putString("ISI", articlesItems.get(position).getContent());
                data.putString("PENULIS", articlesItems.get(position).getAuthor());
                data.putString("TANGGAL", articlesItems.get(position).getPublishedAt());
                data.putString("DESKRIPSI", articlesItems.get(position).getDescription());
                data.putString("SUMBER", articlesItems.get(position).getSource().getName());
                data.putString("IMAGE", LINK_IMAGE);
                Intent kirim = new Intent(v.getContext(), DetailActivity.class);
                kirim.putExtras(data);
                context.startActivity(kirim);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPictureBerita;
        TextView txJudul, txSumberBerita;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivPictureBerita = (ImageView)itemView.findViewById(R.id.imvBerita);
            txJudul = (TextView)itemView.findViewById(R.id.txtJudul);
            txSumberBerita = (TextView)itemView.findViewById(R.id.txtSumberBerita);
        }
    }
}
