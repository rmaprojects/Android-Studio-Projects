package com.raka.m.a.calculatorbasicwithconfirmation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class calculatorsection extends AppCompatActivity {
    String result;
    TextView Hasil, info;
    String getformnilaiX, getformNilaiY, getformNilaiZ;
    Button back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculatorsection);
        Hasil = (TextView) findViewById(R.id.txtResult);
        info = (TextView)findViewById(R.id.tvInfo);
        back = (Button)findViewById(R.id.btnBack);

            Bundle dapatkanpackage = getIntent().getExtras();
            //Hasil.setText(String.valueOf(getformNilaiZ));
            info.setText(dapatkanpackage.getString("INFO"));
            Hasil.setText(dapatkanpackage.getString("RESULT"));
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent abc = new Intent(calculatorsection.this, MainActivity.class);
                    startActivity(abc);
                    finish();
                }
            });




    }

}