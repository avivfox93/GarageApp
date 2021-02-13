package com.example.garageapp;

import androidx.room.Dao;
import androidx.room.*;

import java.util.List;

@Dao
public interface UseSessionDao {
    @Query("SELECT * FROM usesession")
    List<UseSession> getAll();
    @Insert
    void insertAll(UseSession... sessions);
    @Insert
    void insert(UseSession session);



    @Query("SELECT * FROM usesession WHERE start = (SELECT MAX(start) FROM usesession)")
    UseSession getLastSession();

    @Delete
    void delete(UseSession session);

    @Query("SELECT SUM(total) FROM usesession")
    long totalSpendsTime();
}