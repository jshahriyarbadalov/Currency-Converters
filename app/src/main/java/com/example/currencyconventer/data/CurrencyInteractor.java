package com.example.currencyconventer.data;

import com.example.currencyconventer.api.RetrofitApi;
import com.example.currencyconventer.model.CurrencyModel;
import com.example.currencyconventer.screen.ui.CurrencyActivity;
import com.example.currencyconventer.screen.ui.CurrencyPresenter;

import retrofit2.Call;

public class CurrencyInteractor {


    public static Call<CurrencyModel> getRates() {
        return RetrofitApi.buildRetrofit().getRates("TRY");
    }

    public static void getBaseCurrency(CurrencyPresenter.CurrencyCallBack currencyCallBack) {
        RetrofitApi.buildRetrofit().getRates().enqueue(currencyCallBack);
    }
}
