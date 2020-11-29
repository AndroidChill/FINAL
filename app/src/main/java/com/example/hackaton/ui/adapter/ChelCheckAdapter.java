package com.example.hackaton.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.ChatChelItemBinding;
import com.example.hackaton.databinding.OrderReactCaseBinding;

public class ChelCheckAdapter extends RecyclerView.Adapter<ChelCheckAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ChatChelItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.chat_chel_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ChatChelItemBinding binding;

        public ViewHolder(ChatChelItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
