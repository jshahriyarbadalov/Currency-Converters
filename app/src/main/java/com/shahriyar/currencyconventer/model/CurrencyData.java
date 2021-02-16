package com.shahriyar.currencyconventer.model;

public class CurrencyData {
    private String currency;
    private Double value;
    private double result = 1.0;


    public CurrencyData(String currency, Double value) {
        this.currency = currency;
        this.value = value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

}
