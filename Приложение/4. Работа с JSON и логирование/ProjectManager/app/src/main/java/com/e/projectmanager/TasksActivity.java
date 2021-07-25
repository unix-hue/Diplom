package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.e.projectmanager.JSONAdapter.JSONAdapter;
import com.e.projectmanager.models.Desk;
import com.e.projectmanager.models.Task;
import com.e.projectmanager.rvadapters.TasksRVAdapter;

import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AppCompatActivity {

    static List<Task> list = new ArrayList<>();
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Button task_creation = findViewById(R.id.button4);

        String json = getIntent().getStringExtra("desk");
        Desk desk = (Desk) JSONAdapter.fromJson(json, Desk.class);

        this.setTitle(desk.getName());

        list = desk.getList();

        rv = findViewById(R.id.rv_tasks);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new TasksRVAdapter(list, this));

        task_creation.setVisibility(View.INVISIBLE);
        task_creation.setOnClickListener(v -> {
            Intent intent = new Intent(TasksActivity.this, TaskCreationActivity.class);
            intent.putExtra("index", getIntent().getIntExtra("index", 0));
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        rv.getAdapter().notifyDataSetChanged();
    }

    static public void completeTask(int i){
        list.remove(i);
    }
}