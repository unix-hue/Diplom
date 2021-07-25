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

public class TaskCreationActivity extends AppCompatActivity {

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);

        Button button = findViewById(R.id.button7);
        EditText name_edit = findViewById(R.id.task_name);
        EditText desc = findViewById(R.id.description);

        //Задаем пользователя
        String[] people = {"Вася Пупкин, vasya@gmail.com"};

        //Настраиваем Spinner
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, people);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //При выборе элемента он записывается в name
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

        button.setOnClickListener(v -> {
            if (name_edit.getText().length() != 0 && desc.getText().length() != 0) {
                Toast toast = Toast.makeText(getApplicationContext(), "Создана доска с исполнителем " + name, Toast.LENGTH_SHORT);
                toast.show();
                this.finish();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Заполните все поля!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}