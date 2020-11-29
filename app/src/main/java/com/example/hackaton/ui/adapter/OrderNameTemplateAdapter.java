package com.example.hackaton.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.OrderReactCaseBinding;

import java.util.ArrayList;
import java.util.List;

public class OrderNameTemplateAdapter extends RecyclerView.Adapter<OrderNameTemplateAdapter.ViewHolder> {

    List<String> list = new ArrayList<>();
    private EditText editText;

    public OrderNameTemplateAdapter(EditText editText){
        this.editText = editText;
        list.add("Следить за работой");
        list.add("Провести замену");
        list.add("Дополнительный контроль");
        list.add("Изменение режима работы установки");
        list.add("Провести визуальный осмотр, контроль");
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
        holder.bind(list.get(position));

        holder.itemView.setOnClickListener(v -> {
            if(editText != null){
                editText.setText(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private OrderReactCaseBinding binding;

        public ViewHolder(OrderReactCaseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String str){
            binding.text.setText(str);
        }
    }

}
