package com.example.app.utils;

import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5,TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.15.14:8080/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create()))
            .build();
}
