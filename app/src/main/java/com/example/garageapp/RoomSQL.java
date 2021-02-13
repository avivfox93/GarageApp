package com.example.garageapp;

import android.content.Context;

import androidx.room.Room;

public class RoomSQL {
    private static SessionDatabase mDatabase;
    private static RoomSQL instance;

    private RoomSQL(Context context) {
        String pName = context.getApplicationContext().getPackageName();
        mDatabase = Room.databaseBuilder(context.getApplicationContext(), SessionDatabase.class, pName + "-GarageDB.db")
                .build();
    }

    public static RoomSQL getInstance() {
        return instance;
    }

    public static RoomSQL init(Context context) {
        if (instance == null) {
            instance = new RoomSQL(context);
        }
        return instance;
    }


    public interface OnUseRequest<T>{
        void dataReady(T result);
    }

    public void getLastSession(final OnUseRequest<UseSession> callback) {
        new Thread(()-> callback.dataReady(mDatabase.sessionDao().getLastSession())).start();
    }

    public void getTotalSpendsTime(final OnUseRequest<Long> callback) {
        new Thread(()-> callback.dataReady(mDatabase.sessionDao().totalSpendsTime())).start();
    }

    public void insertSession(UseSession session) {
        new Thread(() -> mDatabase.sessionDao().insert(session)).start();
    }
}
