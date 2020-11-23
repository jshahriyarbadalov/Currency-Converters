package com.example.currencyconventer.api;

import com.example.currencyconventer.constants.Consts;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    private static OkHttpClient.Builder client;

    @NotNull
    public static ApiService buildRetrofit(){

        client=new OkHttpClient.Builder();
        client.connectTimeout(15, TimeUnit.SECONDS);
        client.readTimeout(15, TimeUnit.SECONDS);
        client.writeTimeout(15, TimeUnit.SECONDS);


        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .baseUrl(Consts.BASE_URL)
                .build()
                .create(ApiService.class);
    }
}
