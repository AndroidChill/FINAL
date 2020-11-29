package com.example.hackaton.ui.fragment.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hackaton.R;
import com.example.hackaton.databinding.FragmentOrderBinding;
import com.example.hackaton.databinding.OrderItemBinding;
import com.example.hackaton.ui.adapter.EmployeeAdapter;
import com.example.hackaton.ui.adapter.OrderCommentAdapter;
import com.example.hackaton.ui.adapter.OrderReactAdapter;
import com.example.hackaton.ui.adapter.OrderReactCaseAdapter;

public class OrderFragment extends AppCompatActivity {

    private OrderItemBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.order_item);

        binding.rvUchast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvUchast.setAdapter(new EmployeeAdapter());

        binding.rvComment.setLayoutManager(new LinearLayoutManager(this));
        binding.rvComment.setAdapter(new OrderReactAdapter());

        binding.rvReact.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvReact.setAdapter(new OrderReactCaseAdapter());

        binding.rvCommentComment.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCommentComment.setAdapter(new OrderCommentAdapter());
    }

}
