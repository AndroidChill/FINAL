package com.example.hackaton.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hackaton.R;
import com.example.hackaton.ui.fragment.createOrder.OrderCreateCaseFragment;

public class CreateOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, OrderCreateCaseFragment.newInstance())
                .commit();
    }
}