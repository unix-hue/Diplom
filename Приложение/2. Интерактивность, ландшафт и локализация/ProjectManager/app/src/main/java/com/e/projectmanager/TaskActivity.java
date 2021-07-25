package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Button button = findViewById(R.id.button6);
        TextView textView = findViewById(R.id.textView10);
        textView.setText("Какое - то особо важное и занимающее много места описание задачи.");

        button.setOnClickListener(v -> {
            Toast toast = Toast.makeText(getApplicationContext(), "Задача выполнена!", Toast.LENGTH_SHORT);
            toast.show();
            this.finish();
        });
    }
}