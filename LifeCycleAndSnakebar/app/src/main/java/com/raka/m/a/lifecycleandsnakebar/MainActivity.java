package com.raka.m.a.lifecycleandsnakebar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btntosecondactivity, keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btntosecondactivity = (Button) findViewById(R.id.backmainact);
        Toast.makeText(this, "On Create first step", Toast.LENGTH_SHORT).show();
        btntosecondactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(abc);
                finish();
            }
        });
    }
    //Ctrl + Insert --> Override Methodes --> Ketik Methode
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "OnStart is run after on create", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Activity Closed(OnStop)", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "OnResume is Run after activity back online or back from another apps or apps is already paused", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Activity Destroyed(OnDestroy)", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "OnPause is started after activity or app is closed to another apps", Toast.LENGTH_SHORT).show();
    }
}