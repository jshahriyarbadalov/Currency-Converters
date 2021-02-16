package com.shahriyar.currencyconventer.screen.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shahriyar.currencyconventer.R;
import com.shahriyar.currencyconventer.model.CurrencyData;
import com.shahriyar.currencyconventer.screen.ui.view.CurrencyView;
import com.shahriyar.currencyconventer.utils.CurrencyUtil;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CurrencyNameRatesAdapter extends RecyclerView.Adapter<CurrencyNameRatesAdapter.CurrencyRatesViewHolder> {
    private CurrencyView view;
    private List<CurrencyData> ratesNameList;

    public CurrencyNameRatesAdapter(CurrencyView view) {
        this.view = view;
        ratesNameList = new ArrayList<>();
    }

    @NonNull
    @Override
    public CurrencyRatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency_rates_names, parent, false);
        return new CurrencyRatesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRatesViewHolder holder, int position) {
        holder.onBind(ratesNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return ratesNameList.size();
    }

    public void addRatesNameList(@NotNull List<CurrencyData> dataList) {
        ratesNameList.clear();
        ratesNameList.addAll(dataList);
        notifyDataSetChanged();
    }

    public class CurrencyRatesViewHolder extends RecyclerView.ViewHolder {
        View root1;
        TextView currentValue1;
        TextView changedValue1;
        ImageView imageViewFlag1;

        public CurrencyRatesViewHolder(@NonNull View itemView) {
            super(itemView);
            root1=itemView.findViewById(R.id.root1);
            currentValue1 = itemView.findViewById(R.id.current_value1);
            changedValue1 = itemView.findViewById(R.id.changed_value1);
            imageViewFlag1=itemView.findViewById(R.id.imageViewFlag1);
        }

        public void onBind(CurrencyData currencyData) {
            NumberFormat formatter = new DecimalFormat("#0.0000");

            String formattedValue = formatter.format(
                    currencyData.getResult()) + " " + currencyData.getCurrency();
             currentValue1.setText(formattedValue);
              imageViewFlag1.setImageResource(CurrencyUtil.getImageIdByImageName(itemView.getContext(),currencyData.getCurrency()));

            root1.setOnClickListener(v -> {
                view.onCurrencyItemClicked(currencyData);
            });
        }
    }
}
