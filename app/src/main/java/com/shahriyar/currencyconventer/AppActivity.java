package com.shahriyar.currencyconventer;

import android.app.Application;

import com.shahriyar.currencyconventer.dagger.AppModule;
import com.shahriyar.currencyconventer.dagger.CurrencyComponent;
import com.shahriyar.currencyconventer.dagger.DaggerCurrencyComponent;
import com.shahriyar.currencyconventer.dagger.RetrofitModule;
import com.shahriyar.currencyconventer.utils.PrefsUtil;



public class AppActivity extends Application {

    private CurrencyComponent component;
    private PrefsUtil prefsUtil;

    @Override
    public void onCreate(){
        super.onCreate();
        prefsUtil = new PrefsUtil(this);
        component= DaggerCurrencyComponent.builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule(this)).build();
    }

    public PrefsUtil getPrefsUtil() {
        return prefsUtil;
    }
    public CurrencyComponent getComponent() {
        return component;
    }
}
