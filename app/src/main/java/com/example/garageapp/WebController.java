package com.example.garageapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebController implements Callback<Garage> {

    private static final String BASE_URL = "https://pastebin.com/raw/";
    private OnGarageCallback callback;

    public WebController(OnGarageCallback callback){this.callback = callback;}

    public void fetchGarage(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        WebAPI garageAPI = retrofit.create(WebAPI.class);

        Call<Garage> call = garageAPI.loadGarage();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Garage> call, Response<Garage> response) {
        callback.onResponse(response.body());
    }

    @Override
    public void onFailure(Call<Garage> call, Throwable t) {
        t.printStackTrace();
    }

    public interface OnGarageCallback{
        void onResponse(Garage garage);
    }
}
