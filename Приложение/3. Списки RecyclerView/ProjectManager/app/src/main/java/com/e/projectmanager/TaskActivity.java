package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        this.setTitle(getIntent().getStringExtra("task"));

        Button button = findViewById(R.id.button6);

        TextView name = findViewById(R.id.textView5);
        TextView desc = findViewById(R.id.textView10);
        TextView worker = findViewById(R.id.textView);

        name.setText(getIntent().getStringExtra("task"));
        desc.setText(getIntent().getStringExtra("desc"));
        worker.setText(getIntent().getStringExtra("worker"));

        button.setOnClickListener(v -> {
            TasksActivity.completeTask(getIntent().getIntExtra("index", 0));
            this.finish();
        });
    }
}