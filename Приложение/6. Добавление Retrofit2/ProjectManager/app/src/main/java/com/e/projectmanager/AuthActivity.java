package com.e.projectmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.projectmanager.retrofit.RetrofitFactory;
import com.e.projectmanager.retrofit.models.UserPass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        Button button = findViewById(R.id.button2);
        EditText password_edit = findViewById(R.id.password_edit_2);

        RetrofitFactory.makeRequest().getPassword(RetrofitFactory.getCurrent_user()).enqueue(new Callback<UserPass>() {
            @Override
            public void onResponse(@NonNull Call<UserPass> call, @NonNull Response<UserPass> response) {
                assert response.body() != null;
                password = response.body().getPassword();
            }

            @Override
            public void onFailure(@NonNull Call<UserPass> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        button.setOnClickListener(v -> {
            if (password_edit.getText().length() != 0) {
                if (password_edit.getText().toString().equals(password)) {
                    Intent intent = new Intent(AuthActivity.this, DeskActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Неверный пароль!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Введите пароль!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}