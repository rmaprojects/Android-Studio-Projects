package com.raka.m.a.app.biodata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Deklarasikan EditText dan Button
    EditText inNamaLengkap, inEmail, inAlamat, inJK, inAsalSekolah, inTanggalLahir, inHobi;
    Button btnErase, btnSaveView;
    //Buat Variable Penampungan untuk menampung data dari Inputan EditText
    String getNamaLengkap, getEmail, getAlamat, getJK, getAsalSekolah, getTanggalLahir, getHobi, getnull;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Membuat Builder untuk alert dialog
        final AlertDialog.Builder builderAlertDialog = new AlertDialog.Builder(this);

        //Instansiasikan EditText
        inNamaLengkap = (EditText)findViewById(R.id.edtNamaLengkap);
        inEmail = (EditText)findViewById(R.id.edtEmail);
        inAlamat = (EditText)findViewById(R.id.edtAlamat);
        inJK = (EditText)findViewById(R.id.edtJK);
        inAsalSekolah = (EditText)findViewById(R.id.edtAsalSekolah);
        inTanggalLahir = (EditText)findViewById(R.id.edtTanggalLahir);
        inHobi = (EditText)findViewById(R.id.edtHobi);



        btnErase = (Button)findViewById(R.id.btnErase);
        btnSaveView = (Button)findViewById(R.id.btnSaveView);

        btnErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Text was Erased Successfully", Toast.LENGTH_SHORT).show();
                inNamaLengkap.setText("");
                inEmail.setText("");
                inAlamat.setText("");
                inJK.setText("");
                inAsalSekolah.setText("");
                inTanggalLahir.setText("");
                inHobi.setText("");

            }
        });

        btnSaveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNamaLengkap = inNamaLengkap.getText().toString();
                getEmail = inEmail.getText().toString();
                getAlamat = inAlamat.getText().toString();
                getJK = inJK.getText().toString();
                getAsalSekolah = inAsalSekolah.getText().toString();
                getHobi = inHobi.getText().toString();
                getTanggalLahir = inTanggalLahir.getText().toString();

                builderAlertDialog
                        .setTitle("Confirm Section")
                        .setMessage("Nama Lengkap: " + getNamaLengkap + "\n" +
                                    "Email: " + getEmail + "\n" +
                                    "Alamat: " + getAlamat +
                                    "Jenis Kelamin: " + getJK + "\n" +
                                    "Asal Sekolah: " + getAsalSekolah + "\n" +
                                    "Tanggal Lahir: " + getTanggalLahir + "\n" +
                                    "Hobi: " + getHobi + "\n" +
                                    "Send all of your data? Confirm?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Text was Processed Successfully", Toast.LENGTH_LONG).show();
                                //startActivity(new Intent(MainActivity.this, DetailActivity.class));

                                //Do Action Kirim data pakai Intent Bundle (Multiple Data)
                                Bundle packageData = new Bundle();
                                packageData.putString("NAMA_LENGKAP", getNamaLengkap);
                                packageData.putString("EMAIL", getEmail);
                                packageData.putString("ALAMAT", getAlamat);
                                packageData.putString("JENIS_KELAMIN", getJK);
                                packageData.putString("ASAL_SEKOLAH", getAsalSekolah);
                                packageData.putString("TANGGAL_LAHIR", getTanggalLahir);
                                packageData.putString("HOBI", getHobi);
                                packageData.putString("NULL", getnull);

                                Intent KirimPackageData = new Intent(MainActivity.this, DetailActivity.class);
                                KirimPackageData.putExtras(packageData);
                                startActivity(KirimPackageData);

                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Toast.makeText(MainActivity.this, "Action Cancelled", Toast.LENGTH_SHORT).show();

                            }
                        });
                AlertDialog alertDialog = builderAlertDialog.create();
                alertDialog.show();

            }
        });
    }
}