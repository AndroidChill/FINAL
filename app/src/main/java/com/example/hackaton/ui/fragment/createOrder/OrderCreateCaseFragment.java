package com.example.hackaton.ui.fragment.createOrder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.hackaton.R;
import com.example.hackaton.databinding.FragmentCreateOrderCaseBinding;

public class OrderCreateCaseFragment extends Fragment {

    private FragmentCreateOrderCaseBinding binding;

    public static OrderCreateCaseFragment newInstance() {

        Bundle args = new Bundle();

        OrderCreateCaseFragment fragment = new OrderCreateCaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_order_case, container, false);

        binding.caseText.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left,
                            R.anim.slide_out_rigth, 0, 0)
                    .replace(R.id.container, OrderCreateNameTextFragment.newInstance())
                    .commit();
        });

        binding.caseVoice.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left,
                            R.anim.slide_out_rigth, 0, 0)
                    .replace(R.id.container, OrderRecordFragment.newInstance())
                    .commit();
        });

        return binding.getRoot();
    }
}
