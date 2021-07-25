package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.e.projectmanager.dao.Query;
import com.e.projectmanager.dao.models.Task;

public class TaskActivity extends AppCompatActivity {

    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Thread thread = new Thread() {
            @Override
            synchronized public void run(){
                super.run();
                task = Query.getAccess().getTask(getIntent().getLongExtra("index", -1));
            }
        };
        thread.start();

        try {
            Thread.sleep(150);
            this.setTitle(task.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Button button = findViewById(R.id.button6);

        TextView name = findViewById(R.id.textView5);
        TextView desc = findViewById(R.id.textView10);
        TextView worker = findViewById(R.id.textView);

        name.setText(task.getName());
        desc.setText(task.getDescription());
        worker.setText(task.getOwner());

        button.setOnClickListener(v -> {
            Thread thread1 = new Thread() {
                @Override
                synchronized public void run(){
                    super.run();
                    Query.getAccess().completeTask(task);
                }
            };
            thread1.start();

            this.finish();
        });
    }
}