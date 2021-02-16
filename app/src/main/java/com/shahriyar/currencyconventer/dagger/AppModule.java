package com.shahriyar.currencyconventer.dagger;

import android.content.Context;

import com.shahriyar.currencyconventer.AppActivity;
import com.shahriyar.currencyconventer.utils.PrefsUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private AppActivity appActivity;

    public AppModule(AppActivity appActivity) {
        this.appActivity = appActivity;
    }


    @Provides
    @Singleton
    Context provideApplicationContext() {
        return appActivity;
    }

    @Provides
    PrefsUtil providePrefsUtils() {
        return new PrefsUtil(appActivity);
    }

}
