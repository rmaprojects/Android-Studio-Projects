package com.raka.m.a.spinnertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        TextView textView = (TextView)findViewById(R.id.txt_bundle);
        Bundle bundle = getIntent().getExtras();
        String data = bundle.get("NEGARA").toString();
        textView.setText("Negara: " + data);
    }
}