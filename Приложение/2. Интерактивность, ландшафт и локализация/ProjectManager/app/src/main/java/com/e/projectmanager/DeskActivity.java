package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class DeskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk);

        //Через Intent получаем ранее посланые данные по ключу и выводим
        Toast toast = Toast.makeText(getApplicationContext(), "Привет " + getIntent().getStringExtra("name"), Toast.LENGTH_SHORT);
        toast.show();

        Button desk = findViewById(R.id.desk);
        Button creation = findViewById(R.id.create_desk);

        desk.setOnClickListener(v -> {
            Intent intent = new Intent(DeskActivity.this, TasksActivity.class);
            startActivity(intent);
        });

        creation.setOnClickListener(v -> {
            Intent intent = new Intent(DeskActivity.this, DeskCreationActivity.class);
            startActivity(intent);
        });
    }
}