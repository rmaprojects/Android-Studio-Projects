package com.raka.m.a.app.biodata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    //Deklarasi View
    TextView getformNamaLengkap, getformEmail, getformAlamat, getformJK, getformAsalSekolah, getformTanggalLahir, getformHobi;
    Button back2biodatainput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata_detail);


        //Instansiasi Text View
        getformNamaLengkap = (TextView)findViewById(R.id.getnamalengkap);
        getformEmail = (TextView)findViewById(R.id.getEmail);
        getformAlamat = (TextView)findViewById(R.id.getAlamat);
        getformJK = (TextView)findViewById(R.id.getJK);
        getformAsalSekolah = (TextView)findViewById(R.id.getAsalSekolah);
        getformTanggalLahir = (TextView)findViewById(R.id.getTanggalLahir);
        getformHobi = (TextView)findViewById(R.id.gethobi);
        //Get Biodata from activity biodata input
        Bundle dapatkanpackage = getIntent().getExtras();

         getformNamaLengkap.setText(dapatkanpackage.getString("NAMA_LENGKAP"));
         getformEmail.setText(dapatkanpackage.getString("EMAIL"));
         getformAlamat.setText(dapatkanpackage.getString("ALAMAT"));
         getformJK.setText(dapatkanpackage.getString("JENIS_KELAMIN"));
         getformAsalSekolah.setText(dapatkanpackage.getString("ASAL_SEKOLAH"));
         getformTanggalLahir.setText(dapatkanpackage.getString("TANGGAL_LAHIR"));
         getformHobi.setText(dapatkanpackage.getString("HOBI"));


        back2biodatainput = (Button)findViewById(R.id.btnBacktoBiodataInput);
        back2biodatainput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}