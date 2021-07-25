package com.e.projectmanager.models;

import java.util.ArrayList;
import java.util.List;

public class Desk {
    private String name;
    private int index;
    private List<Task> list= new ArrayList<>();

    private static int count = 0;

    public Desk(String name) {
        this.name = name;
        index = count;
        count += 1;
    }

    public String getName() {
        return name;
    }

    public int getIndex(){
        return index;
    }

    public List<Task> getList(){
        return list;
    }

    public void addItem(Task task){
        list.add(task);
    }
}
