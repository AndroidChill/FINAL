package com.example.hackaton.ui.fragment.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hackaton.R;
import com.example.hackaton.databinding.FragmentChatListBinding;
import com.example.hackaton.ui.activity.ChatCreateActivity;
import com.example.hackaton.ui.adapter.ChatListAdapter;

public class ChatListFragment extends Fragment {

    private FragmentChatListBinding binding;

    public static ChatListFragment newInstance() {

        Bundle args = new Bundle();

        ChatListFragment fragment = new ChatListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_list, container, false);

        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(new ChatListAdapter());

        binding.addTask.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), ChatCreateActivity.class));
        });

        return binding.getRoot();
    }
}
