package com.raka.m.a.lifecycleandsnakebar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    Button kembalikeawal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        kembalikeawal = (Button)findViewById(R.id.backtomain);
        kembalikeawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bca = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(bca);
                finish();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Kembali ke sini lagi?", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Second Activity Started!", Toast.LENGTH_SHORT).show();
    }
}