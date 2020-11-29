package com.example.hackaton.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.ExecutorItemBinding;
import com.example.hackaton.model.CheckTask;

import java.util.List;

public class CheckTaskAdapter extends RecyclerView.Adapter<CheckTaskAdapter.ViewHolder>{

    private List<CheckTask> checkTaskList;

    public CheckTaskAdapter(List<CheckTask> checkTaskList){
        this.checkTaskList = checkTaskList;
    }

    @NonNull
    @Override
    public CheckTaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExecutorItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.executor_item, parent, false);
        return new CheckTaskAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckTaskAdapter.ViewHolder holder, int position) {
        CheckTask checkTask = checkTaskList.get(position);
        holder.nameTV.setText(checkTask.getName());
    }

    @Override
    public int getItemCount() {
        return checkTaskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ExecutorItemBinding binding;

        final TextView nameTV;

        public ViewHolder(ExecutorItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            nameTV = binding.nameTV;
        }
    }

}
