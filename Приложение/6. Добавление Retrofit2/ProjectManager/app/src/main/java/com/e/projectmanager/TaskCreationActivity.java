package com.e.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.e.projectmanager.retrofit.RetrofitFactory;
import com.e.projectmanager.retrofit.models.Task;
import com.e.projectmanager.retrofit.models.UserData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskCreationActivity extends AppCompatActivity {

    String worker;
    List<String> people = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);

        Button button = findViewById(R.id.button7);
        EditText name_edit = findViewById(R.id.task_name);
        EditText desc = findViewById(R.id.description);

        TaskCreationActivity taskCreationActivity = this;
        RetrofitFactory.makeRequest().getUsersData().enqueue(new Callback<List<UserData>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserData>> call, @NonNull Response<List<UserData>> response) {
                people.clear();
                assert response.body() != null;
                for(UserData i : response.body()){
                    people.add(i.getData());
                }

                Spinner spinner = findViewById(R.id.spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(taskCreationActivity, android.R.layout.simple_spinner_item, people);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        worker = (String)parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { }
                };
                spinner.setOnItemSelectedListener(itemSelectedListener);

            }

            @Override
            public void onFailure(@NonNull Call<List<UserData>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        button.setOnClickListener(v -> {
            if (name_edit.getText().length() != 0 && desc.getText().length() != 0) {
                Task task = new Task();
                task.setName(name_edit.getText().toString());
                task.setDescription(desc.getText().toString());
                task.setWorker(worker);
                task.setDesk_id(getIntent().getIntExtra("index", -1));

                RetrofitFactory.makeRequest().addTask(task).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) { }

                    @Override
                    public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });

                this.finish();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Заполните все поля!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}