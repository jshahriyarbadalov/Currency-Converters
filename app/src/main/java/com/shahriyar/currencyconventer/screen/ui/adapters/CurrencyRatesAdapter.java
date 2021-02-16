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

public class CurrencyRatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static CurrencyView view;

    private List<CurrencyData> ratesList;

    public CurrencyRatesAdapter(CurrencyView view) {
        this.view = view;
        ratesList = new ArrayList<>();
        // TODO: 24.11.2020

    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 1) {
            return 0;
        }
        return 1;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == 0) {
            view = layoutInflater.inflate(R.layout.item_currency, parent, false);
            return new CurrencyViewHolderOne(view);
        }
            view = layoutInflater.inflate(R.layout.item_currency_reverse, parent, false);
            return new CurrencyViewHolderTwo(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position % 2 == 1) {
            CurrencyViewHolderOne currencyViewHolderOne = (CurrencyViewHolderOne) holder;
            currencyViewHolderOne.onBind(ratesList.get(position));
        } else {
            CurrencyViewHolderTwo currencyViewHolderTwo = (CurrencyViewHolderTwo) holder;
            currencyViewHolderTwo.onBind2(ratesList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return ratesList.size();
    }

    public void addRatesList(@NotNull List<CurrencyData> dataList) {
        ratesList.clear();
        ratesList.addAll(dataList);
        notifyDataSetChanged();
    }

    public static class CurrencyViewHolderOne extends RecyclerView.ViewHolder {

        View root;
        TextView currentValue;
        TextView changedValue;
        ImageView imageViewFlag;

        public CurrencyViewHolderOne(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            currentValue = itemView.findViewById(R.id.current_value);
            changedValue = itemView.findViewById(R.id.changed_value);
            imageViewFlag = itemView.findViewById(R.id.imageViewFlag);
        }

        public void onBind(CurrencyData currencyData) {
            NumberFormat formatter = new DecimalFormat("#0.0000");

            String formattedValue = formatter.format(
                    currencyData.getResult()) + " " + currencyData.getCurrency();
            currentValue.setText(formattedValue);
            imageViewFlag.setImageResource(CurrencyUtil.getImageIdByImageName(itemView.getContext(), currencyData.getCurrency()));

            root.setOnClickListener(v -> {
                view.onCurrencyItemClicked(currencyData);
            });
        }
    }

    public static class CurrencyViewHolderTwo extends RecyclerView.ViewHolder {

        View root2;
        TextView currentValue2;
        TextView changedValue2;
        ImageView imageViewFlag2;

        public CurrencyViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            root2 = itemView.findViewById(R.id.root2);
            currentValue2 = itemView.findViewById(R.id.current_value2);
            changedValue2 = itemView.findViewById(R.id.changed_value2);
            imageViewFlag2 = itemView.findViewById(R.id.imageViewFlag2);
        }

        public void onBind2(CurrencyData currencyData2) {
            NumberFormat formatter = new DecimalFormat("#0.0000");

            String formattedValue = formatter.format(
                    currencyData2.getResult()) + " " + currencyData2.getCurrency();
            currentValue2.setText(formattedValue);
            imageViewFlag2.setImageResource(CurrencyUtil.getImageIdByImageName(itemView.getContext(), currencyData2.getCurrency()));

            root2.setOnClickListener(v -> {
                view.onCurrencyItemClicked(currencyData2);
            });
        }
    }

}
