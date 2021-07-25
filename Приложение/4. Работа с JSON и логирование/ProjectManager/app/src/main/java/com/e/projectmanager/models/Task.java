package com.e.projectmanager.models;

public class Task {
    private final String name;
    private final String description;
    private final String worker;
    private int index;

    public Task(String name, String description, String worker) {
        this.name = name;
        this.description = description;
        this.worker = worker;
    }

    public int getIndex(){return index;}

    public void setIndex(int index){this.index = index;}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getWorker() {
        return worker;
    }
}
