package com.raka.m.a.kalkulatortext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import javax.microedition.khronos.egl.EGLDisplay;

public class MainActivity extends AppCompatActivity {
    EditText edtInputX, edtInputY;
    Button btnPenjumlahan, btnPengurangan, btnPerkalian, btnPembagian;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtInputX = (EditText)findViewById(R.id.edtNilaiX);
        edtInputY = (EditText)findViewById(R.id.edtNilaiY);
        btnPembagian = (Button) findViewById(R.id.btnPembagian);
        btnPengurangan = (Button) findViewById(R.id.btnPengurangan);
        btnPenjumlahan = (Button)findViewById(R.id.btnPertambahan);
        btnPerkalian = (Button)findViewById(R.id.btnPerkalian);
        tvOutput = (TextView)findViewById(R.id.txtOutputProgram);


        btnPenjumlahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_x = Integer.parseInt(edtInputX.getText().toString());
                int nilai_y = Integer.parseInt(edtInputY.getText().toString());
                int nilaiZ;
                nilaiZ = nilai_x + nilai_y;
                tvOutput.setText("Hasil dari " + nilai_x + " + " + nilai_y + " adalah " + nilaiZ);
            }
        });
        btnPengurangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_x = Integer.parseInt(edtInputX.getText().toString());
                int nilai_y = Integer.parseInt(edtInputY.getText().toString());
                int nilaiZ;
                nilaiZ = nilai_x - nilai_y;
                tvOutput.setText("Hasil dari " + nilai_x + " - " + nilai_y + " adalah " + nilaiZ);
            }
        });
        btnPerkalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_x = Integer.parseInt(edtInputX.getText().toString());
                int nilai_y = Integer.parseInt(edtInputY.getText().toString());
                int nilaiZ;
                nilaiZ = nilai_x * nilai_y;
                tvOutput.setText("Hasil dari " + nilai_x + " x " + nilai_y + " adalah " + nilaiZ);
            }
        });
        btnPembagian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_x = Integer.parseInt(edtInputX.getText().toString());
                int nilai_y = Integer.parseInt(edtInputY.getText().toString());
                int nilaiZ;
                nilaiZ = nilai_x / nilai_y;
                tvOutput.setText("Hasil dari " + nilai_x + " ÷ " + nilai_y + " adalah " + nilaiZ);
            }
        });
    }
}