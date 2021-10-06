package com.raka.m.a.portalberitasmk.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.raka.m.a.portalberitasmk.R;
import com.raka.m.a.portalberitasmk.model.InformasiSmkItem;
import com.raka.m.a.portalberitasmk.view.ActivityDetailBerita;

import java.util.List;


public class AdapterListBeritaSMK extends RecyclerView.Adapter<AdapterListBeritaSMK.MyViewholder>{

    Context context;
    List<InformasiSmkItem> ListItemSMK;

    public AdapterListBeritaSMK(Context context, List<InformasiSmkItem> ListItemSMK) {
        this.context = context;
        this.ListItemSMK = ListItemSMK;
    }

    @Override
    public MyViewholder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_portalberita, parent, false);
        MyViewholder holder = new MyViewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        //Holder untuk menampilkan teks dari internet ke sini | getJudulBerita dapetnya dari InformasiSmkItem(Model)
        holder.TxTJudulBerita.setText(ListItemSMK.get(position).getJudulBerita());
        holder.TxTHeadline.setText(ListItemSMK.get(position).getHeadlineBerita());
        holder.TxTWartawan.setText(ListItemSMK.get(position).getNamaPenulis());
        holder.Tanggal.setText(ListItemSMK.get(position).getTanggalPosting());

        //Masukkan Picture dengan URL Link seperti ini:
        String namagambar = ListItemSMK.get(position).getPictureBerita();
        String urlgambarberita = "http://192.168.3.3/portalesemka/picture/" + namagambar;
        //Glide untuk menampilkan picture dari eksekusi link dari internet
        Glide.with(context).load(urlgambarberita).into(holder.ImgBerita);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LOG", "BERITA: " + ListItemSMK.get(position).getJudulBerita());
                Bundle data = new Bundle();
                data.putString("JUDUL", ListItemSMK.get(position).getJudulBerita());
                data.putString("HEADLINE", ListItemSMK.get(position).getHeadlineBerita());
                data.putString("WARTAWAN", ListItemSMK.get(position).getNamaPenulis());
                data.putString("ISIBERITA", ListItemSMK.get(position).getIsiBerita());
                data.putString("TANGGAL", ListItemSMK.get(position).getTanggalPosting());
                data.putString("IMAGE", urlgambarberita);

                Intent kirim = new Intent(v.getContext(), ActivityDetailBerita.class);
                kirim.putExtras(data);
                context.startActivity(kirim);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListItemSMK.size();
    }


    public class MyViewholder extends RecyclerView.ViewHolder{

        ImageView ImgBerita;
        TextView TxTJudulBerita, TxTWartawan, Tanggal, TxTHeadline;

        public MyViewholder(View itemView) {
            super(itemView);
            ImgBerita = (ImageView)itemView.findViewById(R.id.gbrBerita);
            TxTJudulBerita = (TextView)itemView.findViewById(R.id.jdlBerita);
            TxTWartawan = (TextView)itemView.findViewById(R.id.wartaBerita);
            TxTHeadline = (TextView)itemView.findViewById(R.id.headBerita);
            Tanggal = (TextView)itemView.findViewById(R.id.tglPosting);
        }
    }
}