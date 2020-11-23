package com.example.currencyconventer.screen.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.currencyconventer.data.CurrencyInteractor;
import com.example.currencyconventer.model.CurrencyData;
import com.example.currencyconventer.model.CurrencyModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyPresenter {

    private CurrencyView view;


    public CurrencyPresenter(CurrencyView view) {
        this.view = view;
        defaultData();
    }

    public static boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void updateData() {
        CurrencyInteractor.getRates().enqueue(new CurrencyCallBack(view));
    }

    public void defaultData() {
        CurrencyInteractor.getBaseCurrency(new CurrencyCallBack(view));
    }

    public static class CurrencyCallBack implements Callback<CurrencyModel> {
        private CurrencyView view;

        public CurrencyCallBack(@NotNull CurrencyView view) {
            this.view = view;
        }

        @Override
        public void onResponse(@NotNull Call<CurrencyModel> call, @NotNull Response<CurrencyModel> response) {
            if (response.isSuccessful()) {
                if (response.body().getDate() != null)
                    view.showDate(response.body().getDate());
                if (response.body().getBaseCurrency() != null)
                    view.setBaseCurrency(response.body().getBaseCurrency());
                if (response.body().getRates() != null) {
                    HashMap<String, Double> rates = response.body().getRates();
                    List<CurrencyData> rateList = new ArrayList<>();
                    for (Map.Entry<String, Double> item : rates.entrySet()) {
                        rateList.add(new CurrencyData(item.getKey(), item.getValue()));
                    }
                    view.setRates(rateList);
                }
            }else{
                view.showError("Data don't load success!");
            }
        }

        @Override
        public void onFailure(@NotNull Call<CurrencyModel> call, @NotNull Throwable t) {
            view.showError("Error. Server don't load data! ");
        }
    }
}
