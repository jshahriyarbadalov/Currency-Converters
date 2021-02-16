package com.shahriyar.currencyconventer.screen.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.shahriyar.currencyconventer.AppActivity;
import com.shahriyar.currencyconventer.R;
import com.shahriyar.currencyconventer.api.ApiService;
import com.shahriyar.currencyconventer.model.CurrencyData;
import com.shahriyar.currencyconventer.screen.ui.adapters.CurrencyAdapter;
import com.shahriyar.currencyconventer.utils.NetworkConnectionUtil;
import com.shahriyar.currencyconventer.utils.PrefsUtil;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CurrencyActivity extends AppCompatActivity implements CurrencyView {

    @BindView(R.id.root)
    View root;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.editTextSearch)
    EditText editTextSearch;
    @BindView(R.id.textViewCurrency)
    MaterialTextView textViewCurrency;
    @BindView(R.id.editTextAmount)
    EditText editTextAmount;
    @BindView(R.id.recycleViewCurrencies)
    RecyclerView recycleViewCurrencies;
    @Inject
    ApiService apiService;
    @Inject
    PrefsUtil prefsUtil;
    private CurrencyPresenter presenter;
    private CurrencyAdapter adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        recycleViewInit();

        AppActivity component = (AppActivity) getApplication();
        component.getComponent().inject(this);
        component.getPrefsUtil().getCurrencyDataList();


        presenter = new CurrencyPresenter(this, component.getPrefsUtil());

        editTextAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.updateList(s.length() > 0 ? Double.parseDouble(s.toString()) : 1d);
            }
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.searchList(s.length() > 0 ? s.toString() : "");
            }
        });

    }

    void recycleViewInit() {
        adapter = new CurrencyAdapter(this);
        recycleViewCurrencies.setLayoutManager(new LinearLayoutManager(this));
        recycleViewCurrencies.setAdapter(adapter);
    }

    @OnClick(R.id.refresh)
    public void onRefreshClicked() {
        presenter.updateData("EUR");
        editTextAmount.setText("");
        date.setText(R.string.load_wait);
    }


    @Override
    public void showDate(String data) {
        boolean isConnection = NetworkConnectionUtil.isOnline(this);
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
    public Activity getActivity() {
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

    @Override
    public void onCurrencyItemClicked(CurrencyData currencyData) {
        presenter.updateData(currencyData.getCurrency());
        editTextAmount.setText("");
    }

    public void btnNameRatesOnClick(View view) {
        Intent intent =new Intent(this, CurrencyNameRatesActivity.class);
        startActivity(intent);
    }
}