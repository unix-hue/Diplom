package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.e.projectmanager.dao.Query;
import com.e.projectmanager.dao.models.Task;

import java.util.ArrayList;
import java.util.List;

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

        Thread thread = new Thread() {
            @Override
            synchronized public void run(){
                super.run();
                people.clear();
                people.addAll(Query.getAccess().getUsersData());
            }
        };
        thread.start();

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, people);
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

        button.setOnClickListener(v -> {
            if (name_edit.getText().length() != 0 && desc.getText().length() != 0) {
                Task task = new Task();
                task.setName(name_edit.getText().toString());
                task.setDescription(desc.getText().toString());
                task.setOwner(worker);
                task.setDesk_id(getIntent().getLongExtra("index", -1));

                Thread thread1 = new Thread() {
                    @Override
                    synchronized public void run(){
                        super.run();
                        Query.getAccess().addTask(task);
                    }
                };
                thread1.start();

                this.finish();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Заполните все поля!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}