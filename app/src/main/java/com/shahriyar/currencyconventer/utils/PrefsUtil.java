package com.shahriyar.currencyconventer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

public class PrefsUtil {

    private static final String SHARED_NAME = "CurrencyConverter";
    private static final String SAMPLE_STRING = "SAMPLE_STRING";
    private static final String CURRENCY_DATA_LIST = "CURRENCY DATA LIST";

    private final Context context;
    private final SharedPreferences.Editor editor;
    private final SharedPreferences prefs;


    @Inject
    public PrefsUtil(@NotNull Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();

    }

    public void putCurrencyDataList(Set<String> response) {
        Set<String> set = new HashSet<>();
        set.addAll(response);
        editor.putStringSet(CURRENCY_DATA_LIST, set);
        editor.commit();
    }

    public Set<String> getCurrencyDataList() {
        Set<String> set =new HashSet<>();
        set.add("no_date");
        return prefs.getStringSet(CURRENCY_DATA_LIST, set);
    }


    public void putSampleString(String st) {
        editor.putString(SAMPLE_STRING, st).commit();
    }

    public String getSampleString() {
        return prefs.getString(SAMPLE_STRING, "no_date");
    }
}
