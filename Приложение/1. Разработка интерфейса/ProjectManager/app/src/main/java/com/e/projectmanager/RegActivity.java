package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

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

        EditText name_edit = findViewById(R.id.name_edit);
        EditText surname_edit = findViewById(R.id.surname_edit);
        EditText password_edit = findViewById(R.id.password_edit);

        button.setOnClickListener(v -> {
            String name = name_edit.getText().toString();
            String surname = surname_edit.getText().toString();
            String password = password_edit.getText().toString();

            if(name.length() != 0 && surname.length() != 0 && password.length() != 0){
                Toast toast = Toast.makeText(getApplicationContext(), "Здравствуйте, " + name + " " + surname + "!" , Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Введите все данные!", Toast.LENGTH_SHORT);
                toast.show();
            }

        });
    }
}