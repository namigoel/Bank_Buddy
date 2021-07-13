package com.example.amexmate;

import android.view.Display;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {

    @GET("/")
    Call<Model> getAllPhotos();
}
