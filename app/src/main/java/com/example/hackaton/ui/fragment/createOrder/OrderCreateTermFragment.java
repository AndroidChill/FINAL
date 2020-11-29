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

import com.example.hackaton.R;
import com.example.hackaton.databinding.FragmentCreateOrderTermBinding;

public class OrderCreateTermFragment extends Fragment {

    private FragmentCreateOrderTermBinding binding;

    public static OrderCreateTermFragment newInstance() {

        Bundle args = new Bundle();

        OrderCreateTermFragment fragment = new OrderCreateTermFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_order_term, container, false);

        String[] cities = new String[31];

        for(int i = 0; i < 31; i++){
            cities[i] = String.valueOf(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cities);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        binding.count.setAdapter(adapter);

        String[] cities1 = {"Час", "Сутки", "Неделя", "Месяц", "Год"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cities1);
        // Определяем разметку для использования при выборе элемента
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        binding.unit.setAdapter(adapter1);

        return binding.getRoot();
    }
}
