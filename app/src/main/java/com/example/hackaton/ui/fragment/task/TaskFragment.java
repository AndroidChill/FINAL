package com.example.hackaton.ui.fragment.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.OnClick;
import com.example.hackaton.R;
import com.example.hackaton.databinding.FragmentTaskBinding;
import com.example.hackaton.databinding.FragmentTaskNewBinding;
import com.example.hackaton.model.Task;
import com.example.hackaton.ui.activity.CreateOrderActivity;
import com.example.hackaton.ui.activity.FilterTasksActivity;
import com.example.hackaton.ui.activity.MainActivity;
import com.example.hackaton.ui.activity.SortingTasksActivity;
import com.example.hackaton.ui.adapter.TaskAdapter;
import com.example.hackaton.ui.fragment.order.OrderFragment;
import com.example.hackaton.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskFragment extends Fragment implements OnClick {

    private FragmentTaskNewBinding binding;

    private List<Task> taskList;
    private TaskAdapter taskAdapter;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;

    public static TaskFragment newInstance() {

        Bundle args = new Bundle();

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_new, container, false);

        sharedPreferences = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);

        binding.sort.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), SortingTasksActivity.class));
        });

        binding.filtr.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), FilterTasksActivity.class));
        });

        VerticalSpacingItemDecorator verticalSpacingItemDecorator = new VerticalSpacingItemDecorator(30);
        taskList = new ArrayList<>();
        recyclerView = binding.recyclerView;
        taskList.add(new Task("Проверка оборудования", "Куликов Д. А.", "Проверка оборудования",
                "Высокий", "8:00", "8:30"));
        taskList.add(new Task("Встреча с Президентом", "Куликов Д. А.", "Соц. медиа",
                "Не срочно", "11:21", "13:30"));
        taskList.add(new Task("Уборка помещения", "Заммоев А. В.", "Уборка",
                "Средний", "8:00", "18:00"));
        taskList.add(new Task("Рассказать как прошла встреча", "Куликов Д. А.", "Соц. медиа",
                "Не срочно", "11:23", "16:30"));
        taskAdapter = new TaskAdapter(this::onClick);
        recyclerView.addItemDecoration(verticalSpacingItemDecorator);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(taskAdapter);

        taskAdapter.setTaskList(taskList);

        binding.addTask.setOnClickListener(v -> {
            ((MainActivity)getActivity()).startActivity(new Intent(getContext(), CreateOrderActivity.class));
        });

        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume() {
        super.onResume();

        String str = "";
        if(sharedPreferences.getBoolean("uborka", false)){
            str += "Уборка";
            sharedPreferences.edit()
                    .putBoolean("uborka", false).apply();
        }
        if(sharedPreferences.getBoolean("soc", false)){
            str += "Соц. медиа";
            sharedPreferences.edit()
                    .putBoolean("soc", false).apply();
        }
        if(sharedPreferences.getBoolean("check", false)){
            str += "Проверка оборудования";
            sharedPreferences.edit()
                    .putBoolean("check", false).apply();
        }
        if(sharedPreferences.getBoolean("remont", false)){
            str += "Ремонт";
            sharedPreferences.edit()
                    .putBoolean("remont", false).apply();
        }
        if(sharedPreferences.getBoolean("proizvod", false)){
            str += "Производство";
            sharedPreferences.edit()
                    .putBoolean("proizvod", false).apply();
        }
        if(sharedPreferences.getBoolean("otchet", false)){
            str += "Отчетность";
            sharedPreferences.edit()
                    .putBoolean("otchet", false).apply();
        }

        if(sharedPreferences.getBoolean("isEdit", false)){
            if(sharedPreferences.getBoolean("sortingType", false)){
                taskAdapter.sortingType();
                sharedPreferences.edit().putBoolean("sortingType", false).apply();
            }
            if(sharedPreferences.getBoolean("sortingGaveTime", false)){
                taskAdapter.sortingGaveTime();
                sharedPreferences.edit().putBoolean("sortingGaveTime", false).apply();
            }
            if(sharedPreferences.getBoolean("sortingSubordination", false)){
                taskAdapter.sortingSubordination();
                sharedPreferences.edit().putBoolean("sortingSubordination", false).apply();
            }
            if(sharedPreferences.getBoolean("sortingTime", false)){
                taskAdapter.sortingTime();
                sharedPreferences.edit().putBoolean("sortingTime", false).apply();
            }
            sharedPreferences.edit().putBoolean("isEdit", false).apply();
        }
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskList){
            if(task.getTypeOfWork().contains(str)){
                tasks.add(task);
            }
        }
        taskAdapter.setTaskList(tasks);
    }

    @Override
    public void onClick() {
        startActivity(new Intent(getContext(), OrderFragment.class));
    }
}
