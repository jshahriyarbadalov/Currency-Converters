package com.shahriyar.currencyconventer.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;


public class CurrencyModel {

    @SerializedName("base")
    @Expose
    private String baseCurrency;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rates")
    @Expose
    private HashMap<String, Double> rates;


    public CurrencyModel(String baseCurrency, String date, HashMap<String, Double> rates) {
        this.baseCurrency = baseCurrency;
        this.date = date;
        this.rates = rates;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }



}
