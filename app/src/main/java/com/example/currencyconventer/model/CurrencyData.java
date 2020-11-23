package com.example.currencyconventer.model;

public class CurrencyData {
    private String currency;
    private Double value;

    public CurrencyData(String currency, Double value) {
        this.currency = currency;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
