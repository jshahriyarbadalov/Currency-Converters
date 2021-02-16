package com.shahriyar.currencyconventer.api;

import com.shahriyar.currencyconventer.constants.Consts;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    private OkHttpClient.Builder client;

    public RetrofitApi() {
        client=new OkHttpClient.Builder();
        client.connectTimeout(15, TimeUnit.SECONDS);
        client.readTimeout(15, TimeUnit.SECONDS);
        client.writeTimeout(15, TimeUnit.SECONDS);
    }

    @NotNull
    public  ApiService buildRetrofit(){

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .baseUrl(Consts.BASE_URL)
                .build()
                .create(ApiService.class);
    }
}
