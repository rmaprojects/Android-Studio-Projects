package com.raka.m.a.spinnertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner spinnerone = (Spinner)findViewById(R.id.spinner1);
        Button button = (Button)findViewById(R.id.button);

        spinnerone.setOnItemSelectedListener(this);

        List<String> kategori_negara = new ArrayList<>();
        kategori_negara.add("Indonesia");
        kategori_negara.add("Malaysia");
        kategori_negara.add("Amerika");
        kategori_negara.add("Philipina");
        kategori_negara.add("Australia");
        kategori_negara.add("Palestina");
        kategori_negara.add("Thailand");

        ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, kategori_negara);

        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerone.setAdapter(dataadapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(MainActivity.this, Activity2.class);
                abc.putExtra("NEGARA", String.valueOf(spinnerone.getSelectedItem()));
                startActivity(abc);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Selected Country is " + item, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}