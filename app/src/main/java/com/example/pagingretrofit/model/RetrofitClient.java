package com.example.pagingretrofit.model;

import com.example.pagingretrofit.api.ImageResourceApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://pixabay.com/api/";

    private static RetrofitClient client;
    private ImageResourceApi api;

    private RetrofitClient(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ImageResourceApi.class);
    }

    public static synchronized RetrofitClient getRetrofitClient(){
        if (client == null){
            client = new RetrofitClient();
        }
        return client;
    }

    public ImageResourceApi getApi(){
        return api;
    }
}
