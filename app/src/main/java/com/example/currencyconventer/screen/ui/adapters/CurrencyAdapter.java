package com.example.currencyconventer.screen.ui.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyconventer.R;
import com.example.currencyconventer.model.CurrencyData;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private List<CurrencyData> newList = new ArrayList<>();
    private List<CurrencyData> originalList = new ArrayList<>();

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false);

        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        holder.onBind(newList.get(position));
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    public void addItem(CurrencyData data) {
        newList.add(data);
        originalList.add(data);
        notifyItemInserted(newList.size() - 1);
    }

    public void addList(@NotNull List<CurrencyData> dataList) {
        for (CurrencyData data : dataList) {
            addItem(data);
        }
    }

    public void update(double enteredValue) {
        newList.clear();
        for (int i = 0; i < originalList.size(); i++) {
            newList.add(new CurrencyData(originalList.get(i).getCurrency(),
                    originalList.get(i).getValue() * enteredValue));
            notifyItemChanged(i);
        }
    }


    public class CurrencyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root)
        View root;

        @BindView(R.id.current_value)
        TextView currentValue;

        @BindView(R.id.changed_value)
        TextView changedValue;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            root.setOnClickListener(view -> {
                currentValue.getText();
            });
        }

        public void onBind(CurrencyData currencyData) {
            NumberFormat formatter = new DecimalFormat("#0.0000");

            String formattedValue = formatter.format(
                    currencyData.getValue()) + " " + currencyData.getCurrency();
            currentValue.setText(formattedValue);
        }
    }
}
