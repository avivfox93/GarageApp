package com.example.garageapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UseSession.class}, version = 1)
public abstract class SessionDatabase extends RoomDatabase {
    public abstract UseSessionDao sessionDao();
}
