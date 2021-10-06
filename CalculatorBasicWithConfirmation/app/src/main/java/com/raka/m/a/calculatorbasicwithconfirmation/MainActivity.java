package com.raka.m.a.calculatorbasicwithconfirmation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtInputX, edtInputY;
    Button btnPenjumlahan, btnPengurangan, btnPerkalian, btnPembagian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AlertDialog.Builder builderAlertDialog = new AlertDialog.Builder(this);

        edtInputX = (EditText)findViewById(R.id.edtNilaiX);
        edtInputY = (EditText)findViewById(R.id.edtNilaiY);
        btnPembagian = (Button) findViewById(R.id.btnPembagian);
        btnPengurangan = (Button) findViewById(R.id.btnPengurangan);
        btnPenjumlahan = (Button)findViewById(R.id.btnPertambahan);
        btnPerkalian = (Button)findViewById(R.id.btnPerkalian);


        btnPenjumlahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int nilaiZ;
                final int nilai_y = Integer.parseInt(edtInputY.getText().toString().trim());
                final int nilai_x = Integer.parseInt(edtInputX.getText().toString().trim());
                nilaiZ = nilai_x + nilai_y;
                //Penampung hasil
                final String nilaiA = Integer.toString(nilaiZ);
                final String result;
                final String info;
                result = " Adalah " + "" + nilaiA;
                info = "Hasil dari penjumlahan " + nilai_x + " + " + nilai_y;
                builderAlertDialog
                        .setTitle("Confirm Section")
                        .setMessage("Apakah " + nilai_x + " + " + nilai_y + " yang ingin dihitung?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Bundle packageData = new Bundle();
                                packageData.putString("RESULT", result);
                                packageData.putString("INFO", info);

                                Intent KirimPackageData = new Intent(MainActivity.this, calculatorsection.class);
                                KirimPackageData.putExtras(packageData);
                                startActivity(KirimPackageData);
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Process Canceled", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alertDialog = builderAlertDialog.create();
                alertDialog.show();

            }
        });
        btnPengurangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int nilai_x = Integer.parseInt(edtInputX.getText().toString());
                final int nilai_y = Integer.parseInt(edtInputY.getText().toString());
                final int nilaiZ;
                final String result;
                nilaiZ = nilai_x - nilai_y;
                final String info;
                final String nilaiA = Integer.toString(nilaiZ);
                info = "Hasil dari pengurangan " + nilai_x + " dan " + nilai_y;
                result = " Adalah " + "" + nilaiA;
                builderAlertDialog
                        .setTitle("Confirm Section")
                        .setMessage("Apakah " + nilai_x + " - " + nilai_y + " yang ingin dihitung?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Bundle packageData = new Bundle();
                                packageData.putString("RESULT", result);
                                packageData.putString("INFO", info);
                                Intent KirimPackageData = new Intent(MainActivity.this, calculatorsection.class);
                                KirimPackageData.putExtras(packageData);
                                startActivity(KirimPackageData);
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Process Canceled", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alertDialog = builderAlertDialog.create();
                alertDialog.show();
            }
        });
        btnPerkalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int nilai_x = Integer.parseInt(edtInputX.getText().toString());
                final int nilai_y = Integer.parseInt(edtInputY.getText().toString());
                final int nilaiZ;
                final String result;
                nilaiZ = nilai_x * nilai_y;
                final String nilaiA = Integer.toString(nilaiZ);
                final String info;
                info = "Hasil dari perkalian " + nilai_x + " x " + nilai_y;
                result = " Adalah" + "" + nilaiA;
                builderAlertDialog
                        .setTitle("Confirm Section")
                        .setMessage("Apakah " + nilai_x + " x " + nilai_y + " yang ingin dihitung?")

                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Bundle packageData = new Bundle();
                                packageData.putString("RESULT", result);
                                packageData.putString("INFO", info);

                                Intent KirimPackageData = new Intent(MainActivity.this, calculatorsection.class);
                                KirimPackageData.putExtras(packageData);
                                startActivity(KirimPackageData);
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Process Canceled", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alertDialog = builderAlertDialog.create();
                alertDialog.show();
            }
        });
        btnPembagian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int nilai_x = Integer.parseInt(edtInputX.getText().toString());
                final int nilai_y = Integer.parseInt(edtInputY.getText().toString());
                final int nilaiZ;
                final String result;
                nilaiZ = nilai_x / nilai_y;
                final String nilaiA = Integer.toString(nilaiZ);
                final String info;
                result = " Adalah" + "" + nilaiA;
                info = "Hasil dari pembagian " + nilai_x + " ÷ " + nilai_y;
                builderAlertDialog
                        .setTitle("Confirm Section")
                        .setMessage("Apakah " + nilai_x + " ÷ " + nilai_y + " yang ingin dihitung?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Bundle packageData = new Bundle();
                                packageData.putString("RESULT", result);
                                packageData.putString("INFO", info);


                                Intent KirimPackageData = new Intent(MainActivity.this, calculatorsection.class);
                                KirimPackageData.putExtras(packageData);
                                startActivity(KirimPackageData);
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Process Canceled", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alertDialog = builderAlertDialog.create();
                alertDialog.show();
            }
        });
    }
}