package com.example.currencyconventer.screen.ui;

import android.content.Context;
import com.example.currencyconventer.model.CurrencyData;
import java.util.List;

public interface CurrencyView  {

     void showDate(String data);
     void setBaseCurrency(String baseCurrency);
     Context getContext();
     void setRates(List<CurrencyData> rates);
     void showError(String errorData);


}
