package com.shahriyar.currencyconventer.screen.ui.view;

import android.app.Activity;
import android.content.Context;

import com.shahriyar.currencyconventer.model.CurrencyData;

import java.util.List;

public interface CurrencyView {

    void showDate(String data);

    void setBaseCurrency(String baseCurrency);

    Context getContext();

    Activity getActivity();

    void setRates(List<CurrencyData> rates);

    void showError(String errorData);

    void onCurrencyItemClicked(CurrencyData currencyData);
}
