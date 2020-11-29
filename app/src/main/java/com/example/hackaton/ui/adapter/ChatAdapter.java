package com.example.hackaton.ui.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.MyMessageBinding;
import com.example.hackaton.databinding.TheirMessageBinding;
import com.example.hackaton.model.Message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private int TYPE_HOLDER;
    private List<Message> list = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ChatAdapter(){
        list.stream().sorted(Comparator.comparing(Message::getId));
    }

    public void setList(List<Message> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItem(Message message){
        boolean active = false;
        for(Message message1 : list){
            if (message.getId() == message1.getId()){
                active = true;
            }
        }
        if(!active){
            int position = list.size();
            list.add(position, message);
            notifyItemInserted(position);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case 0:
                TheirMessageBinding binding = DataBindingUtil.inflate(inflater, R.layout.their_message, parent, false);
                return new ViewHolder(binding);
            case 1:
                MyMessageBinding binding1 = DataBindingUtil.inflate(inflater, R.layout.my_message, parent, false);
                return new ViewHolder(binding1);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getAuthor().equals("Nikita")){
            return 1;
        } else
            return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TheirMessageBinding theirMessageBinding;
        private MyMessageBinding myMessageBinding;

        public ViewHolder(TheirMessageBinding binding) {
            super(binding.getRoot());
            this.theirMessageBinding = binding;

        }

        public ViewHolder(MyMessageBinding binding) {
            super(binding.getRoot());
            this.myMessageBinding = binding;
        }

        public void bind(Message message){
            if(myMessageBinding == null){
                theirMessageBinding.author.setText(message.getAuthor());
                theirMessageBinding.text.setText(message.getText());
            } else {
                myMessageBinding.text.setText(message.getText());
            }
        }
    }

}
