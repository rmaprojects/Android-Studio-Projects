package com.raka.m.a.tampilkanangka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button tampilkan;
    EditText nilai;
    TextView textout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tampilkan = (Button)findViewById(R.id.btntampilkan);
        nilai = (EditText)findViewById(R.id.nilaitampil);
        textout = (TextView)findViewById(R.id.txtOutputProgram);

        tampilkan.setOnClickListener(v -> {
            int nilaiinput = Integer.parseInt(nilai.getText().toString());
            textout.setText("Your input is " + nilaiinput);
        });
    }
}