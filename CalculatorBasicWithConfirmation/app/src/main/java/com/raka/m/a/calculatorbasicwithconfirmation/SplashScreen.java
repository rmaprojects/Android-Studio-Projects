package com.raka.m.a.calculatorbasicwithconfirmation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Intent rma = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(rma);
    }}