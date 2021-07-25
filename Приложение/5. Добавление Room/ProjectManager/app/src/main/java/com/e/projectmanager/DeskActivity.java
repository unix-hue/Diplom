package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.e.projectmanager.dao.Query;
import com.e.projectmanager.dao.models.Desk;
import com.e.projectmanager.rvadapters.DeskRVAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeskActivity extends AppCompatActivity {

    static List<Desk> list = new ArrayList<>();
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk);

        Thread thread = new Thread() {
            @Override
            synchronized public void run(){
                super.run();
                list.clear();
                list.addAll(Query.getAccess().getDesks(Query.getCurrent_user()));
            }
        };
        thread.start();

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
        Thread thread = new Thread() {
            @Override
            synchronized public void run(){
                super.run();
                list.clear();
                list.addAll(Query.getAccess().getDesks(Query.getCurrent_user()));
            }
        };
        thread.start();

        try {
            Thread.sleep(150);
            Objects.requireNonNull(rv.getAdapter()).notifyDataSetChanged();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}