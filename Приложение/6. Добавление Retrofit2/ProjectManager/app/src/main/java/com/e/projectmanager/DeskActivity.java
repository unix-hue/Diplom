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
import com.e.projectmanager.rvadapters.DeskRVAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeskActivity extends AppCompatActivity {

    static List<Desk> list = new ArrayList<>();
    RecyclerView rv;
    DeskActivity deskActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk);

        deskActivity = this;
        RetrofitFactory.makeRequest().getDesks(RetrofitFactory.getCurrent_user()).enqueue(new Callback<List<Desk>>() {
            @Override
            public void onResponse(@NonNull Call<List<Desk>> call, @NonNull Response<List<Desk>> response) {
                list.clear();
                assert response.body() != null;
                list.addAll(response.body());

                rv = findViewById(R.id.rv_desk);
                rv.setLayoutManager(new LinearLayoutManager(deskActivity));
                rv.setAdapter(new DeskRVAdapter(list, deskActivity));
            }

            @Override
            public void onFailure(@NonNull Call<List<Desk>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        Button creation = findViewById(R.id.create_desk);
        creation.setOnClickListener(v -> {
            Intent intent = new Intent(DeskActivity.this, DeskCreationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RetrofitFactory.makeRequest().getDesks(RetrofitFactory.getCurrent_user()).enqueue(new Callback<List<Desk>>() {
            @Override
            public void onResponse(@NonNull Call<List<Desk>> call, @NonNull Response<List<Desk>> response) {
                list.clear();
                assert response.body() != null;
                list.addAll(response.body());

                rv = findViewById(R.id.rv_desk);
                rv.setLayoutManager(new LinearLayoutManager(deskActivity));
                rv.setAdapter(new DeskRVAdapter(list, deskActivity));
                Objects.requireNonNull(rv.getAdapter()).notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<List<Desk>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}