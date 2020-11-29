package com.example.hackaton.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.OnClick;
import com.example.hackaton.R;
import com.example.hackaton.databinding.ChatListItemBinding;
import com.example.hackaton.databinding.FragmentCompleteOrderItemBinding;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

public class CompleteOrderAdapter extends RecyclerView.Adapter<CompleteOrderAdapter.ViewHolder> {

    private OnClick listener;

    public CompleteOrderAdapter(OnClick listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentCompleteOrderItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_complete_order_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.btn.setOnClickListener(v -> {
            try {
                listener.onClick();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentCompleteOrderItemBinding binding;

        public ViewHolder(FragmentCompleteOrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
