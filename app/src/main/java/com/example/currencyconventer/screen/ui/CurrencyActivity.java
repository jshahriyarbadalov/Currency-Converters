package com.example.currencyconventer.screen.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;

import com.example.currencyconventer.R;
import com.example.currencyconventer.model.CurrencyData;
import com.example.currencyconventer.screen.ui.adapters.CurrencyAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CurrencyActivity extends AppCompatActivity implements CurrencyView {

    private CurrencyPresenter presenter;
    private CurrencyAdapter adapter;

    @BindView(R.id.root)
    View root;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.textViewCurrency)
    MaterialTextView textViewCurrency;
    @BindView(R.id.editTextAmount)
    EditText editTextAmount;
    @BindView(R.id.recycleViewCurrencies)
    RecyclerView recycleViewCurrencies;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        recycleViewInit();

        presenter = new CurrencyPresenter(this);

        editTextAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.update(s.length() > 0 ? Double.parseDouble(s.toString()) : 1d);
            }
        });

    }

    void recycleViewInit() {
        adapter = new CurrencyAdapter();
        recycleViewCurrencies.setLayoutManager(new LinearLayoutManager(this));
        recycleViewCurrencies.setAdapter(adapter);
    }

    @OnClick(R.id.refresh)
    public void onRefreshClicked() {
        presenter.defaultData();
        editTextAmount.setText("");
        date.setText(R.string.load_wait);
    }


    @Override
    public void showDate(String data) {
        boolean isConnection = presenter.isOnline(this);
        if (isConnection) {
            date.setText(data);
        } else {
            date.setText(R.string.no_connection);
        }
    }

    @Override
    public void setBaseCurrency(String baseCurrency) {
        textViewCurrency.setText(baseCurrency);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setRates(List<CurrencyData> rates) {
        adapter.addList(rates);
    }

    @Override
    public void showError(String errorData) {
        Snackbar.make(root, errorData, Snackbar.LENGTH_LONG).show();
    }


}