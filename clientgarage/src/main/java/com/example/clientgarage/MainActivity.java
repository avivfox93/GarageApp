package com.example.clientgarage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends com.example.garageapp.MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackgroundColor(getResources().getColor(R.color.green, getTheme()));
        setAppName("Garage Client App");
    }
}