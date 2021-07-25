package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.e.projectmanager.models.Desk;
import com.e.projectmanager.models.Task;
import com.e.projectmanager.rvadapters.DeskRVAdapter;

import java.util.ArrayList;
import java.util.List;

public class DeskActivity extends AppCompatActivity {

    //Данные для списка
    static List<Desk> list = new ArrayList<>();

    static String userdata;

    //Переменная для RecyclerView
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk);

        userdata = getIntent().getStringExtra("name") + "," +getIntent().getStringExtra("email");

        //Инициализируем RecyclerView, устанавливаем layoutManager и адаптер
        rv = findViewById(R.id.rv_desk);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new DeskRVAdapter(list, this));

        Button creation = findViewById(R.id.create_desk);

        creation.setOnClickListener(v -> {
            Intent intent = new Intent(DeskActivity.this, DeskCreationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Обновляем список
        rv.getAdapter().notifyDataSetChanged();
    }

    //Методы для изменения списка из других активностей
    static public void editList(Desk desk){
        list.add(desk);
    }

    static public String getUser(){
        return userdata;
    }

    static public List<Task> getList(int index){
        return list.get(index).getList();
    }

    static public void addListItem(int index, Task task){
        list.get(index).addItem(task);
    }
}