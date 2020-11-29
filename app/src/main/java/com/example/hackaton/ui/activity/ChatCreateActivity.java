package com.example.hackaton.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.hackaton.R;
import com.example.hackaton.databinding.ActivityChatCreateBinding;
import com.example.hackaton.ui.adapter.ChatDestinationAdapter;
import com.example.hackaton.ui.adapter.ChelCheckAdapter;
import com.example.hackaton.ui.adapter.EmployeeCheckAdapter;

public class ChatCreateActivity extends AppCompatActivity {

    private ActivityChatCreateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_create);

        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerView2.setAdapter(new ChatDestinationAdapter(binding.editText5));

        binding.rvSearch.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvSearch.setAdapter(new EmployeeCheckAdapter());

        binding.rvEmployee.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvEmployee.setAdapter(new ChelCheckAdapter());

        binding.btn.setOnClickListener(v -> {
            startActivity(new Intent(this, ChatActivity.class));
        });
    }
}