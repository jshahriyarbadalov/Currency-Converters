package com.shahriyar.currencyconventer.screen.ui.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shahriyar.currencyconventer.AppActivity;
import com.shahriyar.currencyconventer.R;
import com.shahriyar.currencyconventer.api.ApiService;
import com.shahriyar.currencyconventer.model.CurrencyData;
import com.shahriyar.currencyconventer.screen.ui.adapters.CurrencyNameRatesAdapter;
import com.shahriyar.currencyconventer.utils.PrefsUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyNameRatesActivity extends AppCompatActivity implements CurrencyView {
    @Inject
    ApiService apiService;
    @Inject
    PrefsUtil prefsUtil;
    @BindView(R.id.recycleViewCurrenciesNames)
    RecyclerView recycleViewCurrenciesNames;
    private CurrencyNameRatesAdapter adapter1;
    private CurrencyPresenter presenter;
    private List<CurrencyData> ratesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_name_rates);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        ratesList = new ArrayList<>();
        AppActivity component = (AppActivity) getApplication();
        component.getComponent().inject(this);
        component.getPrefsUtil().getCurrencyDataList();

        presenter = new CurrencyPresenter(this, component.getPrefsUtil());

        adapter1 = new CurrencyNameRatesAdapter(this);
        recycleViewCurrenciesNames.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleViewCurrenciesNames.setAdapter(adapter1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView;
        searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.searchList(newText);
                return false;
            }
        });
        return true;
    }


    @Override
    public void showDate(String data) {

    }

    @Override
    public void setBaseCurrency(String baseCurrency) {

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
        adapter1.addRatesNameList(rates);
    }

    @Override
    public void showError(String errorData) {

    }

    @Override
    public void onCurrencyItemClicked(CurrencyData currencyData) {
        ratesList.add(currencyData);
    }
}
