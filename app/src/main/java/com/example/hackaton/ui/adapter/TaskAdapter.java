package com.example.hackaton.ui.adapter;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.OnClick;
import com.example.hackaton.R;
import com.example.hackaton.databinding.TaskItemBinding;
import com.example.hackaton.model.Task;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> taskList = new ArrayList<>();
    private OnClick listener;

    public TaskAdapter(OnClick listener) {
        this.listener = listener;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortingType(){
        taskList.sort(Comparator.comparing(Task:: getPriorityType));
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortingGaveTime(){
        taskList.sort(Comparator.comparing(Task::getGaveTime));
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortingSubordination(){
        taskList.sort(Comparator.comparing(Task::getSubordination));
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortingTime(){
        taskList.sort(Comparator.comparing(Task::getPeriod));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TaskItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.task_item, parent, false);
        return new TaskAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {

        holder.binding.main.setOnClickListener(v -> {
            try {
                listener.onClick();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });

        Task task = taskList.get(position);
        holder.taskTV.setText(task.getTask());
        holder.subordinationTV.setText(task.getSubordination());
        holder.priorityTV.setText(task.getPriority());
        holder.periodTV.setText(task.getPeriod());
        holder.gaveTimeTV.setText(task.getGaveTime());
        holder.typeOfWorkTV.setText(task.getTypeOfWork());

        switch (holder.priorityTV.getText().toString()) {
            case ("Высокий"):
                holder.priorityTV.setTextColor(Color.RED);
                break;
            case ("Средний"):
                holder.priorityTV.setTextColor(Color.BLACK);
                break;
            case ("Не срочно"):
                holder.priorityTV.setTextColor(Color.GREEN);
                break;


        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TaskItemBinding binding;

        final TextView taskTV, subordinationTV, periodTV, priorityTV, typeOfWorkTV, gaveTimeTV;

        public ViewHolder(TaskItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            taskTV = binding.taskTV;
            subordinationTV = binding.subordinationTV;
            priorityTV = binding.priorityTV;
            periodTV = binding.periodTV;
            typeOfWorkTV = binding.typeOfWorkTV;
            gaveTimeTV = binding.gaveTimeTV;
        }
    }

}
