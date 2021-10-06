package com.raka.m.a.calculatortoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        btnPenjumlahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_x = Integer.parseInt(edtInputX.getText().toString());
                int nilai_y = Integer.parseInt(edtInputY.getText().toString());
                int nilaiZ;
                nilaiZ = nilai_x + nilai_y;
                Toast.makeText(MainActivity.this, "Hasil dari " + nilai_x + " + " + nilai_y + " adalah " + nilaiZ, Toast.LENGTH_LONG).show();
                if (edtInputX == null) {
                    Toast.makeText(MainActivity.this, "No input", Toast.LENGTH_SHORT).show();
                } else if (edtInputY == null) {
                    Toast.makeText(MainActivity.this, "No input", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnPengurangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_x = Integer.parseInt(edtInputX.getText().toString());
                int nilai_y = Integer.parseInt(edtInputY.getText().toString());
                int nilaiZ;
                nilaiZ = nilai_x - nilai_y;
                Toast.makeText(MainActivity.this, "Hasil dari " + nilai_x + " - " + nilai_y + " adalah " + nilaiZ, Toast.LENGTH_LONG).show();
                if (edtInputX == null) {
                    Toast.makeText(MainActivity.this, "No input", Toast.LENGTH_SHORT).show();
                } else if (edtInputY == null) {
                    Toast.makeText(MainActivity.this, "No input", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnPerkalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_x = Integer.parseInt(edtInputX.getText().toString());
                int nilai_y = Integer.parseInt(edtInputY.getText().toString());
                int nilaiZ;
                nilaiZ = nilai_x * nilai_y;
                Toast.makeText(MainActivity.this, "Hasil dari " + nilai_x + " x " + nilai_y + " adalah " + nilaiZ, Toast.LENGTH_LONG).show();
                if (edtInputX == null) {
                    Toast.makeText(MainActivity.this, "No input", Toast.LENGTH_SHORT).show();
                } else if (edtInputY == null) {
                    Toast.makeText(MainActivity.this, "No input", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnPembagian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_x = Integer.parseInt(edtInputX.getText().toString());
                int nilai_y = Integer.parseInt(edtInputY.getText().toString());
                int nilaiZ;
                nilaiZ = nilai_x / nilai_y;
                Toast.makeText(MainActivity.this, "Hasil dari " + nilai_x + " ÷ " + nilai_y + " adalah " + nilaiZ, Toast.LENGTH_LONG).show();
                if (edtInputX == null) {
                    Toast.makeText(MainActivity.this, "No input", Toast.LENGTH_SHORT).show();
                } else if (edtInputY == null) {
                    Toast.makeText(MainActivity.this, "No input", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}