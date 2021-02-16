package com.shahriyar.currencyconventer.dagger;

import com.shahriyar.currencyconventer.api.ApiService;
import com.shahriyar.currencyconventer.screen.ui.view.CurrencyActivity;
import com.shahriyar.currencyconventer.screen.ui.view.CurrencyNameRatesActivity;
import com.shahriyar.currencyconventer.screen.ui.view.CurrencyPresenter;

import dagger.Component;

@Component(modules = { RetrofitModule.class, AppModule.class})
public interface CurrencyComponent {
    ApiService getApiService();

    void inject(CurrencyActivity currencyActivity);
    void inject(CurrencyPresenter currencyPresenter);
    void inject(CurrencyNameRatesActivity ratesActivity);
}
