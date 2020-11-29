package com.example.hackaton.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.ChatListItemBinding;
import com.example.hackaton.databinding.EmployeeItemBinding;
import com.example.hackaton.databinding.FragmentChatListBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHodler> {

    List<String> list = new ArrayList<>();

    public ChatListAdapter(){
        list.add("Вопросы по маркетингу");
        list.add("Провести инструктаж на участке");
        list.add("Соблюдать требования безопасности");
        list.add("Ожидание комиссии");
        list.add("Провести замеры геометрии");
        list.add("Провести визуальный осмотр, контроль");
        list.add("ВСледить за работой");
        list.add("Провести замену");
        list.add("Провести замену\n" +
                "Дополнительный контроль");
        list.add("Изменение режима работы установки");


    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ChatListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.chat_list_item, parent, false);
        return new ViewHodler(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        holder.binding.title.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder{

        private ChatListItemBinding binding;

        public ViewHodler(ChatListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
