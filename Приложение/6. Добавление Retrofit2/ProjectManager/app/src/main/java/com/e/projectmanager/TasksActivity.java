package com.e.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.e.projectmanager.retrofit.RetrofitFactory;
import com.e.projectmanager.retrofit.models.Desk;
import com.e.projectmanager.retrofit.models.Task;
import com.e.projectmanager.rvadapters.TasksRVAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TasksActivity extends AppCompatActivity {

    static List<Task> tasklist = new ArrayList<>();
    RecyclerView rv;
    int id;
    TasksActivity tasksActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Button task_creation = findViewById(R.id.button4);

        id = getIntent().getIntExtra("index", -1);

        rv = findViewById(R.id.rv_tasks);
        tasksActivity = this;
        RetrofitFactory.makeRequest().getDeskName(id).enqueue(new Callback<Desk>() {
            @Override
            public void onResponse(@NonNull Call<Desk> call, @NonNull Response<Desk> response) {
                assert response.body() != null;
                tasksActivity.setTitle(response.body().getName());
            }

            @Override
            public void onFailure(@NonNull Call<Desk> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        RetrofitFactory.makeRequest().getTasks(id).enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(@NonNull Call<List<Task>> call, @NonNull Response<List<Task>> response) {
                tasklist.clear();
                assert response.body() != null;
                tasklist.addAll(response.body());

                rv.setLayoutManager(new LinearLayoutManager(tasksActivity));
                rv.setAdapter(new TasksRVAdapter(tasklist, tasksActivity));
            }

            @Override
            public void onFailure(@NonNull Call<List<Task>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        task_creation.setOnClickListener(v -> {
            Intent intent = new Intent(TasksActivity.this, TaskCreationActivity.class);
            intent.putExtra("index", id);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        RetrofitFactory.makeRequest().getTasks(id).enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(@NonNull Call<List<Task>> call, @NonNull Response<List<Task>> response) {
                tasklist.clear();
                assert response.body() != null;
                tasklist.addAll(response.body());

                rv = findViewById(R.id.rv_tasks);
                rv.setLayoutManager(new LinearLayoutManager(tasksActivity));
                rv.setAdapter(new TasksRVAdapter(tasklist, tasksActivity));
                Objects.requireNonNull(rv.getAdapter()).notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<List<Task>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}