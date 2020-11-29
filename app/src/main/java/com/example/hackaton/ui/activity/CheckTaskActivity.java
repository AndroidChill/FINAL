package com.example.hackaton.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hackaton.R;
import com.example.hackaton.model.CheckTask;
import com.example.hackaton.ui.adapter.CheckTaskAdapter;
import com.example.hackaton.util.MyDatePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckTaskActivity extends AppCompatActivity implements
        MyDatePickerFragment.endDateChangedListener, MyDatePickerFragment.endTimeChangedListener{

    private TextView endTimeTextView, endDateTextView;
    private Calendar calendar;
    private RecyclerView recyclerView;
    private List<CheckTask> checkTaskList;
    private CheckTaskAdapter checkTaskAdapter;
    private Button groupBtn, personBtn, yesBtn, noBtn;

    private static final int PICKFILE_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_task);
        ConstraintLayout endTimeLayout = findViewById(R.id.endTimeLayout);
        HorizontalItemDecorator horizontalItemDecorator = new HorizontalItemDecorator(30);
        endDateTextView = findViewById(R.id.endDateTextView);
        endTimeTextView = findViewById(R.id.endTimeTextView);
        groupBtn = findViewById(R.id.groupBtn);
        personBtn = findViewById(R.id.personBtn);
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);

        calendar = Calendar.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        checkTaskList = new ArrayList<>();

        checkTaskList.add(new CheckTask("E.П. Васильев"));
        checkTaskList.add(new CheckTask("А.К. Антонов"));
        checkTaskList.add(new CheckTask("В.К. Казаков"));
        checkTaskList.add(new CheckTask("Л.Р. Симонов"));
        checkTaskAdapter = new CheckTaskAdapter(checkTaskList);
        recyclerView.addItemDecoration(horizontalItemDecorator);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(checkTaskAdapter);


        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.groupBtn:
                        groupBtn.setBackgroundColor(Color.GREEN);
                        personBtn.setBackgroundColor(Color.parseColor("#D6D7D7"));
                        break;
                    case R.id.personBtn:
                        personBtn.setBackgroundColor(Color.GREEN);
                        groupBtn.setBackgroundColor(Color.parseColor("#D6D7D7"));

                        break;
                    case R.id.yesBtn:
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("file/*");
                        startActivityForResult(intent,PICKFILE_RESULT_CODE);
                        yesBtn.setBackgroundColor(Color.GREEN);
                        noBtn.setBackgroundColor(Color.parseColor("#D6D7D7"));
                        break;
                    case R.id.noBtn:
                        noBtn.setBackgroundColor(Color.GREEN);
                        yesBtn.setBackgroundColor(Color.parseColor("#D6D7D7"));
                        break;
                }
            }
        };

        setInitialDate();
        setInitialTime();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.endTimeLayout:
                        showEndDatePicker();
                        break;
                }
            }
        };
        endTimeLayout.setOnClickListener(onClickListener);
        groupBtn.setOnClickListener(buttonClickListener);
        personBtn.setOnClickListener(buttonClickListener);
        yesBtn.setOnClickListener(buttonClickListener);
        noBtn.setOnClickListener(buttonClickListener);
    }


    private void setInitialDate() {

        CharSequence dateCharSequence = DateFormat.format("EEE, dd MMM yyyy", calendar);
        String dateString = dateCharSequence.toString();
        endDateTextView.setText(dateString);
    }

    private void setInitialTime() {
        CharSequence timeCharSequence = DateFormat.format("h:mm a", calendar);
        String timeString = timeCharSequence.toString();
        endTimeTextView.setText(timeString);
    }

    private void showEndDatePicker() {
        DialogFragment datePickerFragment = new MyDatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "end date picker");
    }

    @Override
    public void changeEndDate(String date) {
        endDateTextView.setText(date);
    }

    @Override
    public void changeEndTime(String time) {
        endTimeTextView.setText(time);
    }

}