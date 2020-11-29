package com.example.hackaton.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.EmployeeItemBinding;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EmployeeItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.employee_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private EmployeeItemBinding binding;

        public ViewHolder(EmployeeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(){}
    }

}
