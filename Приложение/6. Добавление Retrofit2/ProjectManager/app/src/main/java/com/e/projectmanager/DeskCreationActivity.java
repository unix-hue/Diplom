package com.e.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.projectmanager.retrofit.RetrofitFactory;
import com.e.projectmanager.retrofit.models.Desk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeskCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_creation);

        Button button = findViewById(R.id.button5);
        EditText desk_edit = findViewById(R.id.desk_name);

        button.setOnClickListener(v -> {
            if (desk_edit.getText().length() != 0) {
                Desk desk = new Desk();
                desk.setUser_id(RetrofitFactory.getCurrent_user());
                desk.setName(desk_edit.getText().toString());

                RetrofitFactory.makeRequest().addDesk(desk).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
                    }

                    @Override
                    public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });

                this.finish();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Введите название доски!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}