package com.example.hackaton.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.OrderCommentBinding;
import com.example.hackaton.databinding.OrderReactCaseBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatDestinationAdapter extends RecyclerView.Adapter<ChatDestinationAdapter.ViewHolder> {

    private List<String> list = new ArrayList<>();
    private EditText editText;

    public ChatDestinationAdapter(EditText editText){
        list.add("Обсудить будущее компании");
        list.add("Планерка");
        list.add("Обсудить различные ситуации");
        this.editText = editText;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        OrderReactCaseBinding binding = DataBindingUtil.inflate(inflater, R.layout.order_react_case, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.text.setText(list.get(position));

        holder.itemView.setOnClickListener(v -> {
            editText.setText(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private OrderReactCaseBinding binding;

        public ViewHolder(OrderReactCaseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
