package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.e.projectmanager.models.Desk;
import com.e.projectmanager.models.Task;
import com.e.projectmanager.rvadapters.DeskRVAdapter;

import java.util.ArrayList;
import java.util.List;

public class DeskActivity extends AppCompatActivity {

    static List<Desk> list = new ArrayList<>();
    static String userdata;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk);

        userdata = getIntent().getStringExtra("name") + "," +getIntent().getStringExtra("email");

        for(int i = 1; i <= 3; i++){
            Desk desk = new Desk("Desk " + i);
            for(int j = 1; j <= 3; j++){
                desk.addItem(new Task("Task " + j, "Some task " + j, "Василий Пупкин, vasya@gmail.com"));
            }
            list.add(desk);
        }

        rv = findViewById(R.id.rv_desk);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new DeskRVAdapter(list, this));

        Button creation = findViewById(R.id.create_desk);

        creation.setVisibility(View.INVISIBLE);
        creation.setOnClickListener(v -> {
            Intent intent = new Intent(DeskActivity.this, DeskCreationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        rv.getAdapter().notifyDataSetChanged();
    }

    static public void editList(Desk desk){
        list.add(desk);
    }

    static public String getUser(){
        return userdata;
    }

    static public List<Task> getList(int index){
        return list.get(index).getList();
    }

    static public void addListItem(int index, Task task){
        list.get(index).addItem(task);
    }
}