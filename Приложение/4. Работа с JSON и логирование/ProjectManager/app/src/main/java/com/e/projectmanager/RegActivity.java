package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button button = findViewById(R.id.button);
        EditText name = findViewById(R.id.name_edit);
        EditText surname = findViewById(R.id.surname_edit);
        EditText password = findViewById(R.id.password_edit);

        button.setOnClickListener(v -> {
            if (name.getText().length() != 0 && surname.getText().length() != 0 && password.getText().length() != 0) {
                Intent intent = new Intent(RegActivity.this, DeskActivity.class);
                intent.putExtra("name", name.getText().toString() + " " + surname.getText().toString());
                intent.putExtra("email", getIntent().getStringExtra("email"));
                startActivity(intent);
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Заполните все поля!", Toast.LENGTH_SHORT);
                toast.show();
            }

        });
    }
}