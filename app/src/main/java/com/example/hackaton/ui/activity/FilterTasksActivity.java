package com.example.hackaton.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;

import com.example.hackaton.R;
import com.example.hackaton.databinding.ActivityFilterTasksBinding;

public class FilterTasksActivity extends AppCompatActivity {

    private ActivityFilterTasksBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_filter_tasks);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);

        binding.checkboxTypeOfWork1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPreferences.edit()
                            .putBoolean("uborka", true).apply();
                }
            }
        });
        binding.checkboxTypeOfWork2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPreferences.edit()
                            .putBoolean("soc", true).apply();
                }
            }
        });
        binding.checkboxTypeOfWork3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPreferences.edit()
                            .putBoolean("check", true).apply();
                }
            }
        });
        binding.checkboxTypeOfWork4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPreferences.edit()
                            .putBoolean("remont", true).apply();
                }
            }
        });
        binding.checkboxTypeOfWork5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPreferences.edit()
                            .putBoolean("proizvod", true).apply();
                }
            }
        });
        binding.checkboxTypeOfWork1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPreferences.edit()
                            .putBoolean("otchet", true).apply();
                }
            }
        });

    }
}