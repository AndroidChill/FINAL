package com.example.hackaton.ui.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hackaton.R;
import com.example.hackaton.databinding.FragmentOrderListBinding;
import com.example.hackaton.ui.activity.CreateOrderActivity;
import com.example.hackaton.ui.activity.InformationReportActivity;
import com.example.hackaton.ui.activity.MainActivity;

public class OrderListFragment extends Fragment {

    private FragmentOrderListBinding binding;

    public static OrderListFragment newInstance() {

        Bundle args = new Bundle();

        OrderListFragment fragment = new OrderListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_list, container, false);

        binding.addTask.setOnClickListener(v -> {
            ((MainActivity)getActivity()).startActivity(new Intent(getContext(), CreateOrderActivity.class));
        });

        return binding.getRoot();
    }
}
