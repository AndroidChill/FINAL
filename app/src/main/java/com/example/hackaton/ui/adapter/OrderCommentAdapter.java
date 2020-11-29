package com.example.hackaton.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.OrderCommentBinding;
import com.fasterxml.jackson.databind.DatabindContext;

public class OrderCommentAdapter extends RecyclerView.Adapter<OrderCommentAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        OrderCommentBinding binding = DataBindingUtil.inflate(inflater, R.layout.order_comment, parent, false);
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

        private OrderCommentBinding binding;

        public ViewHolder(OrderCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
