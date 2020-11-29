package com.example.hackaton.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.ActivityInformationReportBinding;
import com.example.hackaton.model.Report;
import com.example.hackaton.ui.adapter.ReportAdapter;
import com.example.hackaton.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class InformationReportActivity extends Fragment {

    private ActivityInformationReportBinding binding;

    private List<Report> reportList;
    private ReportAdapter reportAdapter;
    private RecyclerView recyclerView;

    public static InformationReportActivity newInstance() {

        Bundle args = new Bundle();

        InformationReportActivity fragment = new InformationReportActivity();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_information_report, container, false);

        VerticalSpacingItemDecorator verticalSpacingItemDecorator = new VerticalSpacingItemDecorator(30);
        reportList = new ArrayList<>();
        recyclerView = binding.recyclerView;
        reportList.add(new Report("Новогодний корпоратив", "29.11.2020","Руководитель цеха",
                "Всем", "Примите самые искренние поздравления с Новым годом и Рождеством! Пусть этот год станет началом благоприятных перемен и успешных дел и каждый его день будет плодотворным в работе и счастливым в личной жизни. Приглашаем Вас провести незабываемый предновогодний вечер в кругу друзей и коллег. Мы ждем Вас 29 декабря 2020 года в Главном офисе нашей компании в 17:00. Будем рады встрече!"));
        reportList.add(new Report("Придется задержаться на работе",
                "29.11.2020",   "Руководитель цеха",  "Цех №31", "Прошу вас задержаться сегодня на работе"));
        reportAdapter = new ReportAdapter(reportList);
        recyclerView.addItemDecoration(verticalSpacingItemDecorator);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(reportAdapter);

        return binding.getRoot();
    }

}
