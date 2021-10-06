package com.raka.m.a.portalberitasmk.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.raka.m.a.portalberitasmk.R;

public class ActivityDetailBerita extends AppCompatActivity {

    TextView Judul, Headline, Penulis, Isiberita, Tanggal;
    ImageView ImageBerita;
    String namapict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        Judul = (TextView)findViewById(R.id.getJudul);
        Headline = (TextView)findViewById(R.id.getHeadln);
        Penulis = (TextView)findViewById(R.id.getPenulis);
        Isiberita = (TextView)findViewById(R.id.getIsi);
        Tanggal = (TextView)findViewById(R.id.getTanggal);
        ImageBerita = (ImageView)findViewById(R.id.getIMG);

        Bundle tarikpackage = getIntent().getExtras();
        Judul.setText(tarikpackage.getString("JUDUL"));
        Headline.setText(tarikpackage.getString("HEADLINE"));
        Penulis.setText(tarikpackage.getString("WARTAWAN"));
        Isiberita.setText(tarikpackage.getString("ISIBERITA"));
        Tanggal.setText(tarikpackage.getString("TANGGAL"));
        namapict = tarikpackage.getString("IMAGE");
        Glide.with(this).load(namapict).into(ImageBerita);

    }
}