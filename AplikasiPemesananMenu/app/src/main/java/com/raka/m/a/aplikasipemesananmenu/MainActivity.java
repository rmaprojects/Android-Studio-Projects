package com.raka.m.a.aplikasipemesananmenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String pembeli;
    EditText namapembeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner pilihmeja = (Spinner)findViewById(R.id.listmeja);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        Button tampilkandata = (Button)findViewById(R.id.tampilkan);

        namapembeli = (EditText)findViewById(R.id.pembeli);

        pilihmeja.setOnItemSelectedListener(this);

        final List <String> pilihanmeja = new ArrayList<>();
        pilihanmeja.add("Pilih Meja");
        pilihanmeja.add("Meja 1");
        pilihanmeja.add("Meja 2");
        pilihanmeja.add("Meja 3");
        pilihanmeja.add("Meja 4");
        pilihanmeja.add("Meja 5");
        pilihanmeja.add("Meja 6");

        ArrayAdapter<String> dataadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pilihanmeja);
        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pilihmeja.setAdapter(dataadapter);
        pilihanmeja.remove(0);

        tampilkandata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pembeli = namapembeli.getText().toString();
                String spinnertext = pilihmeja.getSelectedItem().toString();
                alertDialog
                        .setTitle("Confirm Section")
                        .setMessage("Nama: " + pembeli + "\nMeja: " + spinnertext)
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Ok Sip", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}