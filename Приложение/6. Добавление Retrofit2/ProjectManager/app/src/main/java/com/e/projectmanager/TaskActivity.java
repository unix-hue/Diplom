package com.e.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.e.projectmanager.retrofit.RetrofitFactory;
import com.e.projectmanager.retrofit.models.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskActivity extends AppCompatActivity {

    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        TaskActivity taskActivity = this;
        RetrofitFactory.makeRequest().getTask(getIntent().getIntExtra("index", -1)).enqueue(new Callback<Task>() {
            @Override
            public void onResponse(@NonNull Call<Task> call, @NonNull Response<Task> response) {
                task = response.body();
                assert task != null;
                taskActivity.setTitle(task.getName());

                TextView name = findViewById(R.id.textView5);
                TextView desc = findViewById(R.id.textView10);
                TextView worker = findViewById(R.id.textView);

                name.setText(task.getName());
                desc.setText(task.getDescription());
                worker.setText(task.getWorker());
            }

            @Override
            public void onFailure(@NonNull Call<Task> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        Button button = findViewById(R.id.button6);
        button.setOnClickListener(v -> {
            RetrofitFactory.makeRequest().completeTask(task.getId()).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) { }

                @Override
                public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                    t.printStackTrace();
                }
            });

            this.finish();
        });
    }
}