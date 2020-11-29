package com.example.hackaton.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.databinding.InformationReportItemBinding;
import com.example.hackaton.model.Report;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder>{

    private List<Report> reportList;

    public ReportAdapter(List<Report> reportList){
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public ReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        InformationReportItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.information_report_item, parent, false);
        return new ReportAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ViewHolder holder, int position) {
        Report report = reportList.get(position);
        holder.headerTV.setText(report.getHeader());
        holder.dateTV.setText(report.getDate());
        holder.jobTV.setText(report.getJob());
        holder.recipientTV.setText(report.getRecipient());
        holder.reportTextTV.setText(report.getReportText());
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        InformationReportItemBinding binding;

        final TextView headerTV, dateTV, recipientTV, jobTV, reportTextTV;
        public ViewHolder(InformationReportItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            headerTV = binding.headerTV;
            dateTV = binding.dateTV;
            jobTV = binding.jobTV;
            recipientTV = binding.recipientTV;
            reportTextTV = binding.reportTextTV;
        }


    }

}
