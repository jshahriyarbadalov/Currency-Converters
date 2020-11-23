package com.example.currencyconventer.api;

import com.example.currencyconventer.constants.Consts;
import com.example.currencyconventer.model.CurrencyModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {


    @GET(Consts.BASE_LATEST_CURRENCIES)
    Call<CurrencyModel> getRates(@Query("base") String path);

    @GET(Consts.BASE_LATEST_CURRENCIES)
    Call<CurrencyModel> getRates();
}
