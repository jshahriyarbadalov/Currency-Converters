package com.shahriyar.currencyconventer.api;

import com.shahriyar.currencyconventer.constants.Consts;
import com.shahriyar.currencyconventer.model.CurrencyModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    @GET(Consts.BASE_LATEST_CURRENCIES)
    Call<CurrencyModel> getRates(@Query("base") String path);

}
