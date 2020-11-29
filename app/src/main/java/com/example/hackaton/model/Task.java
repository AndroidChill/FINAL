package com.example.hackaton.model;

public class Task {

    private String task;
    private String subordination;
    private String priority;
    private String period;
    private String typeOfWork;
    private String gaveTime;

    private int priorityType;

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public String getGaveTime() {
        return gaveTime;
    }

    public void setGaveTime(String gaveTime) {
        this.gaveTime = gaveTime;
    }

    public Task(String task, String subordination, String typeOfWork, String priority, String gaveTime, String period) {
        this.task = task;
        this.subordination = subordination;
        this.typeOfWork = typeOfWork;
        this.priority = priority;
        this.gaveTime = gaveTime;
        this.period = period;

        if (priority.equals("Высокий")){
            priorityType = 2;
        } else  if(priority.equals(("Средний"))){
            priorityType = 1;
        } else  if (priority.equals("Не срочно")){
            priorityType = 0;
        }
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSubordination() {
        return subordination;
    }

    public void setSubordination(String subordination) {
        this.subordination = subordination;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(int priorityType) {
        this.priorityType = priorityType;
    }


}
