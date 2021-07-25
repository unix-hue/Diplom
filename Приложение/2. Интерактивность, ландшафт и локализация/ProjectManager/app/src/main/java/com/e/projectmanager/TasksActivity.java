package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Button task = findViewById(R.id.button3);
        Button task_creation = findViewById(R.id.button4);

        task.setOnClickListener(v -> {
            Intent intent = new Intent(TasksActivity.this, TaskActivity.class);
            startActivity(intent);
        });

        task_creation.setOnClickListener(v -> {
            Intent intent = new Intent(TasksActivity.this, TaskCreationActivity.class);
            startActivity(intent);
        });
    }
}