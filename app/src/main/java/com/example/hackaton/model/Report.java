package com.example.hackaton.model;

public class Report {

    private String header;
    private String date;
    private String job;
    private String recipient;
    private String reportText;

    public Report(String header, String date, String job, String recipient, String reportText) {
        this.header = header;
        this.date = date;
        this.job = job;
        this.recipient = recipient;
        this.reportText = reportText;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

}
