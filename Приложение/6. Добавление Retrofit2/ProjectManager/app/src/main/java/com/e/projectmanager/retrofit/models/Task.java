package com.e.projectmanager.retrofit.models;

public class Task {
    private int id;
    private int desk_id;
    private String worker;
    private String name;
    private String description;

    public Task(){ }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDesk_id() {
        return desk_id;
    }

    public void setDesk_id(int desk_id) {
        this.desk_id = desk_id;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
