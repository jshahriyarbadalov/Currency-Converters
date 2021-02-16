package com.shahriyar.currencyconventer.data;

import com.shahriyar.currencyconventer.api.ApiService;
import com.shahriyar.currencyconventer.model.CurrencyModel;

import javax.inject.Inject;

import retrofit2.Call;


public class CurrencyInteractor {
    @Inject
    ApiService apiService;

    @Inject
    public CurrencyInteractor() {

    }

    public Call<CurrencyModel> getRates(String currency) {
        return apiService.getRates(currency);
    }

}
