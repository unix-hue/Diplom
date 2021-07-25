package com.e.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.projectmanager.retrofit.RetrofitFactory;
import com.e.projectmanager.retrofit.models.LastId;
import com.e.projectmanager.retrofit.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button button = findViewById(R.id.button);
        EditText name = findViewById(R.id.name_edit);
        EditText surname = findViewById(R.id.surname_edit);
        EditText password = findViewById(R.id.password_edit);

        String email = getIntent().getStringExtra("email");

        button.setOnClickListener(v -> {
            String name_str = name.getText().toString();
            String surname_str = surname.getText().toString();
            String password_str = password.getText().toString();

            if (name_str.length() != 0 && surname_str.length() != 0 && password_str.length() != 0) {
                Intent intent = new Intent(RegActivity.this, DeskActivity.class);

                User user = new User();
                user.setName(name_str);
                user.setSurname(surname_str);
                user.setEmail(email);
                user.setPassword(password_str);

                RetrofitFactory.makeRequest().addUser(user).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {

                        RetrofitFactory.makeRequest().getId().enqueue(new Callback<LastId>() {
                            @Override
                            public void onResponse(@NonNull Call<LastId> call, @NonNull Response<LastId> response) {
                                assert response.body() != null;
                                RetrofitFactory.setCurrent_user(response.body().getId());
                            }

                            @Override
                            public void onFailure(@NonNull Call<LastId> call, @NonNull Throwable t) {
                                t.printStackTrace();
                            }
                        });

                    }

                    @Override
                    public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });

                startActivity(intent);
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Заполните все поля!", Toast.LENGTH_SHORT);
                toast.show();
            }

        });
    }
}