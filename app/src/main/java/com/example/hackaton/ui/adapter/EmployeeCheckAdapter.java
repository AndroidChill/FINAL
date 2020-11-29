package com.example.hackaton.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.EmployeeCheckItemBinding;
import com.example.hackaton.databinding.EmployeeItemBinding;

public class EmployeeCheckAdapter extends RecyclerView.Adapter<EmployeeCheckAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EmployeeCheckItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.employee_check_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position % 3 == 0){
            holder.binding.active.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private EmployeeCheckItemBinding binding;

        public ViewHolder(EmployeeCheckItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
