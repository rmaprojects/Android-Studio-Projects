package com.raka.m.a.aplikasilistmenu.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.raka.m.a.aplikasilistmenu.R;


//Jika Merah klik Alt + Enter -> Enter lagi -> Enter lagi
public class AdapterListMenu extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] namaproduk;
    private final String[] kategoriproduk;
    private final String[] khasmakanan;
    private final String[] picture;
    private final int[] hargamakanan;
    private final float[] rating;


    public AdapterListMenu(Activity context, String[] namaproduk, String[] kategoriproduk, String[] khasmakanan, String[] linkpicture, int[] hargamakanan, float[] rating) {
        super(context, R.layout.itemlist_menu, namaproduk);
        this.context = context;
        this.namaproduk = namaproduk;
        this.kategoriproduk = kategoriproduk;
        this.khasmakanan = khasmakanan;
        this.picture = linkpicture;
        this.rating = rating;
        this.hargamakanan = hargamakanan;
    }


    //Klik kiri -> Alt + insert -> Generate -> Cari GetView, Enter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.itemlist_menu, null, true);

        TextView NmMenu, KatMenu, KhsMenu, HargaMenu;
        ImageView ImgMenu;
        RatingBar RateMenu;

        NmMenu = (TextView)rowView.findViewById(R.id.namamenu);
        KatMenu = (TextView)rowView.findViewById(R.id.jenismenu);
        KhsMenu = (TextView)rowView.findViewById(R.id.asaldaerah);
        HargaMenu = (TextView)rowView.findViewById(R.id.hargamenu);
        ImgMenu = (ImageView)rowView.findViewById(R.id.gambarmenu);
        RateMenu = (RatingBar)rowView.findViewById(R.id.ratingmenu);

        NmMenu.setText(namaproduk[position]);
        KatMenu.setText(kategoriproduk[position]);
        KhsMenu.setText(khasmakanan[position]);
        HargaMenu.setText(String.valueOf(hargamakanan[position]));
        HargaMenu.setText("Rp." + hargamakanan[position]);

        Glide.with(context).load(picture[position]).into(ImgMenu);

        RateMenu.setRating(rating[position]);
        return rowView;

    }
}