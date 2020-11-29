package com.example.hackaton.ui.fragment.createOrder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.hackaton.R;
import com.example.hackaton.databinding.FragmentCreateOrderExecutorBinding;
import com.example.hackaton.ui.adapter.EmployeeCheckAdapter;

public class OrderCreateExecutorFragment extends Fragment {

    private FragmentCreateOrderExecutorBinding binding;

    public static OrderCreateExecutorFragment newInstance() {

        Bundle args = new Bundle();

        OrderCreateExecutorFragment fragment = new OrderCreateExecutorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_order_executor, container, false);

        String[] cities = {"Цех производства", "Цех уборки", "Цех Начальства"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cities);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        binding.corpus.setAdapter(adapter);

        String[] cities1 = {"A", "B", "C"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cities1);
        // Определяем разметку для использования при выборе элемента
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        binding.number.setAdapter(adapter1);

        StaggeredGridLayoutManager _sGridLayoutManager =   new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        binding.employee.setLayoutManager(_sGridLayoutManager);
        binding.employee.setAdapter(new EmployeeCheckAdapter());


        binding.btn.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left,
                            R.anim.slide_out_rigth, 0, 0)
                    .replace(R.id.container, OrderCreateTermFragment.newInstance())
                    .commit();
        });
        return binding.getRoot();
    }
}
