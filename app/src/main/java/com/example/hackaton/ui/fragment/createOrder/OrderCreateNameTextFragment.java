package com.example.hackaton.ui.fragment.createOrder;

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
import com.example.hackaton.databinding.FragmentCreateOrderNameTextBinding;
import com.example.hackaton.ui.adapter.OrderNameTemplateAdapter;

public class OrderCreateNameTextFragment extends Fragment {

    private FragmentCreateOrderNameTextBinding binding;

    public static OrderCreateNameTextFragment newInstance() {

        Bundle args = new Bundle();

        OrderCreateNameTextFragment fragment = new OrderCreateNameTextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_order_name_text, container, false);

        binding.templatesTitle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.templatesTitle.setAdapter(new OrderNameTemplateAdapter(binding.editText2));


        binding.btn.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left,
                            R.anim.slide_out_rigth, 0, 0)
                    .replace(R.id.container, OrderCreateExecutorFragment.newInstance())
                    .commit();
        });

        return binding.getRoot();
    }
}
