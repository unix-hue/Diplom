package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.mail_button);
        EditText editText = findViewById(R.id.mail_edit);

        //Тестовый email
        String email = "vasya@gmail.com";

        button.setOnClickListener(v -> {
            Intent intent;

            //Проверка введенного значения на соответствие emailу
            if (isEmail(editText.getText().toString())) {

                //В зависимости от email адреса переходим в разные активности
                if (editText.getText().toString().equals(email)) {
                    intent = new Intent(MainActivity.this, AuthActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, RegActivity.class);
                }
                startActivity(intent);

            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Введено некорректное значение!", Toast.LENGTH_SHORT);
                toast.show();
            }

        });
    }

    //Проверка на соответствие emailу с помощью регулярного выражения
    private boolean isEmail(String text){
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}