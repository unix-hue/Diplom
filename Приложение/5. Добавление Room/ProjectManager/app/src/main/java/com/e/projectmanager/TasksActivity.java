package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.e.projectmanager.dao.Query;
import com.e.projectmanager.dao.models.Task;
import com.e.projectmanager.rvadapters.TasksRVAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TasksActivity extends AppCompatActivity {

    static List<Task> tasklist = new ArrayList<>();
    RecyclerView rv;
    long id;
    String desk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Button task_creation = findViewById(R.id.button4);

        id = getIntent().getLongExtra("index", -1);

        Thread thread = new Thread() {
            @Override
            synchronized public void run(){
                super.run();
                desk = Query.getAccess().getDeskName(id);
                tasklist.clear();
                tasklist.addAll(Query.getAccess().getTasks(id));
            }
        };
        thread.start();

        try {
            Thread.sleep(150);
            this.setTitle(desk);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rv = findViewById(R.id.rv_tasks);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new TasksRVAdapter(tasklist, this));

        task_creation.setOnClickListener(v -> {
            Intent intent = new Intent(TasksActivity.this, TaskCreationActivity.class);
            intent.putExtra("index", id);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread thread = new Thread() {
            @Override
            synchronized public void run(){
                super.run();
                tasklist.clear();
                tasklist.addAll(Query.getAccess().getTasks(id));
            }
        };
        thread.start();

        try {
            Thread.sleep(150);
            Objects.requireNonNull(rv.getAdapter()).notifyDataSetChanged();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}