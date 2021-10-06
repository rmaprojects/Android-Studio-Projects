package com.raka.m.a.aplikasilistmenu.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.raka.m.a.aplikasilistmenu.R;
import com.raka.m.a.aplikasilistmenu.adapter.AdapterListMenu;

public class ListMenuActivity extends AppCompatActivity {

    //Persiapkan data Array

    String[] namamenu = {
            "Gado-gado Spesial",
            "Lontong Sayur",
            "Jus Mangga Spesial",
            "Roti Bakar",
            "Ayam Geprek Madu",
            "Es Jeruk Nipis",
            "Mie Goreng Spesial",
            "Sosis Bakar Jumbo",
            "Pop Ice Cokelat",
            "Nasi Goreng"
    };
    String[] kategorimenu = {
            "Makanan",
            "Makanan",
            "Minuman",
            "Makanan",
            "Makanan",
            "Minuman",
            "Makanan",
            "Makanan",
            "Minuman",
            "Makanan"
    };
    String[] khasmenu = {
            "Betawi",
            "Betawi",
            "Own-made",
            "Own-made",
            "Jawa Tengah",
            "Own-made",
            "Own-made",
            "Own-made",
            "Jepang",
            "Own-made"
    };
    float[] rating = {
            5.0f,
            3.5f,
            4.3f,
            3.0f,
            4.0f,
            5.0f,
            3.5f,
            2.5f,
            5.0f,
            5.0f
    };
    String[] menupicture = {
            "http://192.168.3.2/kafeqita/pict/Gado-Gado.jpg",
            "http://192.168.3.2/kafeqita/pict/Lontong%20Sayur.jpeg",
            "http://192.168.3.2/kafeqita/pict/jus-mangga.png",
            "http://192.168.3.2/kafeqita/pict/RotiBakar.jpg",
            "http://192.168.3.2/kafeqita/pict/AyamGeprek.jpg",
            "http://192.168.3.2/kafeqita/pict/EsJerukNipis.jpg",
            "http://192.168.3.2/kafeqita/pict/mie-goreng.jpg",
            "http://192.168.3.2/kafeqita/pict/03d83d5c00a248048dba59c1a2d2eeae_1571284865171672566.jpeg",
            "http://192.168.3.2/kafeqita/pict/289719-milkshake.jpg",
            "http://192.168.3.2/kafeqita/pict/nasi-goreng-setan.jpg"
    };
    int[] hargaproduk = {
            60000,
            5000,
            4000,
            70000,
            43000,
            2000,
            4000,
            5000,
            40000,
            70000
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmenu);

        AdapterListMenu adplistmenu = new AdapterListMenu(this, namamenu, kategorimenu, khasmenu, menupicture, hargaproduk, rating);
        ListView listmenu = (ListView)findViewById(R.id.ListDataMenu);
        listmenu.setAdapter(adplistmenu);

        listmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posisi, long id) {
                Toast.makeText(ListMenuActivity.this, "Makanan: " + namamenu[posisi], Toast.LENGTH_SHORT).show();
            }
        });
    }
}