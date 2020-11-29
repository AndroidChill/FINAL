package com.example.hackaton.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.OrderReactCaseBinding;

public class OrderReactCaseAdapter extends RecyclerView.Adapter<OrderReactCaseAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        OrderReactCaseBinding binding = DataBindingUtil.inflate(inflater, R.layout.order_react_case, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private OrderReactCaseBinding binding;

        public ViewHolder(OrderReactCaseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
