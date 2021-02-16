package com.shahriyar.currencyconventer.dagger;

import com.shahriyar.currencyconventer.AppActivity;
import com.shahriyar.currencyconventer.api.ApiService;
import com.shahriyar.currencyconventer.constants.Consts;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    private AppActivity appActivity;

    public RetrofitModule(AppActivity appActivity) {
        this.appActivity = appActivity;
    }

    public RetrofitModule() {
    }

    @Provides
    public HttpLoggingInterceptor providesInterceptor(){
        HttpLoggingInterceptor interceptor =new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    @Provides
    public ApiService providesRetrofit(OkHttpClient.Builder client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .baseUrl(Consts.BASE_URL)
                .build()
                .create(ApiService.class);
    }

    @Provides
    public OkHttpClient.Builder provideClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(providesInterceptor());
        client.connectTimeout(15, TimeUnit.SECONDS);
        client.readTimeout(15, TimeUnit.SECONDS);
        client.writeTimeout(15, TimeUnit.SECONDS);

        return client;
    }
}