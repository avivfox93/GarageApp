package com.example.garageapp;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RoomSQL.init(this);
    }
}
