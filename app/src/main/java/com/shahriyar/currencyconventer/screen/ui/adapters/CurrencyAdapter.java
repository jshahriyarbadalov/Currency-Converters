package com.shahriyar.currencyconventer.screen.ui.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shahriyar.currencyconventer.R;
import com.shahriyar.currencyconventer.utils.CurrencyUtil;
import com.shahriyar.currencyconventer.model.CurrencyData;
import com.shahriyar.currencyconventer.screen.ui.view.CurrencyView;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {
    private CurrencyView view;

    private List<CurrencyData> ratesList;

    public CurrencyAdapter(CurrencyView view) {
        this.view = view;
        ratesList = new ArrayList<>();
        // TODO: 24.11.2020

    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        holder.onBind(ratesList.get(position));
    }

    @Override
    public int getItemCount() {
        return ratesList.size();
    }

    public void addList(@NotNull List<CurrencyData> dataList) {
        ratesList.clear();
        ratesList.addAll(dataList);
        notifyDataSetChanged();
    }


    public class CurrencyViewHolder extends RecyclerView.ViewHolder {

        View root;
        TextView currentValue;
        TextView changedValue;
        ImageView imageViewFlag;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            currentValue = itemView.findViewById(R.id.current_value);
            changedValue = itemView.findViewById(R.id.changed_value);
            imageViewFlag=itemView.findViewById(R.id.imageViewFlag);
        }

        public void onBind(CurrencyData currencyData) {
            NumberFormat formatter = new DecimalFormat("#0.0000");

            String formattedValue = formatter.format(
                    currencyData.getResult()) + " " + currencyData.getCurrency();
            currentValue.setText(formattedValue);
            imageViewFlag.setImageResource(CurrencyUtil.getImageIdByImageName(itemView.getContext(),currencyData.getCurrency()));

            root.setOnClickListener(v -> {
                view.onCurrencyItemClicked(currencyData);
            });
        }
    }


}
