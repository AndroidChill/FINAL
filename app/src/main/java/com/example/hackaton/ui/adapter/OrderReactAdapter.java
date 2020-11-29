package com.example.hackaton.ui.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.OrderReactItemBinding;

public class OrderReactAdapter extends RecyclerView.Adapter<OrderReactAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        OrderReactItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.order_react_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private OrderReactItemBinding binding;

        public ViewHolder(OrderReactItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(){}

    }

}
