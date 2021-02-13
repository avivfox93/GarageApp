package com.example.garageapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebAPI {
    @GET("WypPzJCt")
    Call<Garage> loadGarage();
}
