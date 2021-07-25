package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        Button button = findViewById(R.id.button2);
        EditText password_edit = findViewById(R.id.password_edit_2);
        String password = "123456";

        button.setOnClickListener(v -> {
            if (password_edit.getText().length() != 0) {
                if (password_edit.getText().toString().equals(password)) {
                    Intent intent = new Intent(AuthActivity.this, DeskActivity.class);
                    intent.putExtra("name", "Василий Пупкин");
                    intent.putExtra("email", getIntent().getStringExtra("email"));
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