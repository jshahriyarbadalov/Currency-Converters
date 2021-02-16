package com.shahriyar.currencyconventer.screen.ui.view;


import com.shahriyar.currencyconventer.AppActivity;

import com.shahriyar.currencyconventer.data.CurrencyInteractor;
import com.shahriyar.currencyconventer.model.CurrencyData;
import com.shahriyar.currencyconventer.model.CurrencyModel;
import com.shahriyar.currencyconventer.utils.PrefsUtil;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyPresenter {

    private CurrencyView view;
    @Inject
    CurrencyInteractor interactor;
    @Inject
    PrefsUtil prefsUtil;
    private List<CurrencyData> currencyDataList;


    public CurrencyPresenter(CurrencyView view, PrefsUtil prefsUtil) {
        this.view = view;
        this.prefsUtil = prefsUtil;
        AppActivity app = (AppActivity) view.getActivity().getApplication();
        app.getComponent().inject(this);
        currencyDataList = new ArrayList<>();
        updateData("EUR");
    }


    public void updateData(String currency) {
        interactor.getRates(currency).enqueue(new CurrencyCallBack(view));
    }

    public void updateList(double enteredValue) {

        for (CurrencyData data : currencyDataList) {
            data.setResult(data.getValue() * enteredValue);
        }
        view.setRates(currencyDataList);
    }

    public void searchList(String enterCurrency) {
        List<CurrencyData> filterList = new ArrayList<>();
        for (CurrencyData currency : currencyDataList) {
            if (currency.getCurrency().toLowerCase().startsWith(enterCurrency.toLowerCase())) {
                filterList.add(currency);
            }
        }
        view.setRates(filterList);
    }

    public class CurrencyCallBack implements Callback<CurrencyModel> {
        private CurrencyView view;

        public CurrencyCallBack(@NotNull CurrencyView view) {
            this.view = view;

        }

        @Override
        public void onResponse(@NotNull Call<CurrencyModel> call, @NotNull Response<CurrencyModel> response) {
            if (response.isSuccessful()) {
                if (response.body().getDate() != null) {
                    view.showDate(response.body().getDate());
                }
                if (response.body().getBaseCurrency() != null) {
                    view.setBaseCurrency(response.body().getBaseCurrency());
                }
                if (response.body().getRates() != null) {
                    HashMap<String, Double> rates = response.body().getRates();
                    List<CurrencyData> rateList = new ArrayList<>();
                    Set<String> setList = new HashSet<>();
                    for (Map.Entry<String, Double> item : rates.entrySet()) {
                        rateList.add(new CurrencyData(item.getKey(), item.getValue()));
                        setList.add(item.toString());
                    }

                    currencyDataList.clear();
                    currencyDataList.addAll(rateList);
                    prefsUtil.putCurrencyDataList(setList);
                    updateList(1.0);
                }
            } else {
                view.showError("Data don't load success!");
            }
        }

        @Override
        public void onFailure(@NotNull Call<CurrencyModel> call, @NotNull Throwable t) {
            view.showError("Error. Server don't load data! ");
        }

    }


}
