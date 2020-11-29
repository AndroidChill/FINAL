package com.example.hackaton.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.hackaton.R;
import com.example.hackaton.databinding.ActivitySortingTasksBinding;
import com.example.hackaton.model.Task;
import com.example.hackaton.ui.adapter.TaskAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingTasksActivity extends AppCompatActivity {

    private ActivitySortingTasksBinding binding;
    private List<Task> taskList;
    private SharedPreferences sharedPreferences;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting_tasks);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);

        Intent intent = getIntent();
        taskList = (ArrayList<Task>) intent.getSerializableExtra("taskList");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sorting_tasks);

        View.OnClickListener onClickListener = this::chooseElement;

        View.OnClickListener onSortingClickListener = this::sortList;

        binding.sortAscending.setOnClickListener(onSortingClickListener);
        binding.sortDescending.setOnClickListener(onSortingClickListener);

        binding.checkboxPodch.setOnClickListener(onClickListener);
        binding.checkboxPriority.setOnClickListener(onClickListener);
        binding.checkboxGaveTime.setOnClickListener(onClickListener);
        binding.checkboxPeriod.setOnClickListener(onClickListener);
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sortList(View view) {
        switch (view.getId()) {
            case R.id.sortAscending:
                taskList.sort(Comparator.comparing(Task:: getPriorityType));
                break;
        }
    }

    private void chooseElement(View view) {
        switch (view.getId()) {
            case R.id.checkboxPodch:
                binding.checkboxPodch.setChecked(true);
                binding.checkboxPriority.setChecked(false);
                binding.checkboxGaveTime.setChecked(false);
                binding.checkboxPeriod.setChecked(false);
                sharedPreferences.edit().putBoolean("isEdit", true).apply();
                sharedPreferences.edit().putBoolean("sortingSubordination", true).apply();
                break;
            case R.id.checkboxPriority:
                binding.checkboxPodch.setChecked(false);
                binding.checkboxPriority.setChecked(true);
                binding.checkboxGaveTime.setChecked(false);
                binding.checkboxPeriod.setChecked(false);
                sharedPreferences.edit().putBoolean("isEdit", true).apply();
                sharedPreferences.edit().putBoolean("sortingType", true).apply();
                break;
            case R.id.checkboxGaveTime:
                binding.checkboxPodch.setChecked(false);
                binding.checkboxPriority.setChecked(false);
                binding.checkboxGaveTime.setChecked(true);
                binding.checkboxPeriod.setChecked(false);
                sharedPreferences.edit().putBoolean("isEdit", true).apply();
                sharedPreferences.edit().putBoolean("sortingGaveTime", true).apply();
                break;
            case R.id.checkboxPeriod:
                binding.checkboxPodch.setChecked(false);
                binding.checkboxPriority.setChecked(false);
                binding.checkboxGaveTime.setChecked(false);
                binding.checkboxPeriod.setChecked(true);
                sharedPreferences.edit().putBoolean("isEdit", true).apply();
                sharedPreferences.edit().putBoolean("sortingTime", true).apply();
                break;

        }

    }
}