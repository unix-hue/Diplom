package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.e.projectmanager.JSONAdapter.JSONAdapter;
import com.e.projectmanager.models.Task;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        String json = getIntent().getStringExtra("task");
        Task task = (Task) JSONAdapter.fromJson(json, Task.class);

        this.setTitle(task.getName());

        Button button = findViewById(R.id.button6);

        TextView name = findViewById(R.id.textView5);
        TextView desc = findViewById(R.id.textView10);
        TextView worker = findViewById(R.id.textView);

        name.setText(task.getName());
        desc.setText(task.getDescription());
        worker.setText(task.getWorker());

        button.setVisibility(View.INVISIBLE);
        button.setOnClickListener(v -> {
            TasksActivity.completeTask(getIntent().getIntExtra("index", 0));
            this.finish();
        });
    }
}