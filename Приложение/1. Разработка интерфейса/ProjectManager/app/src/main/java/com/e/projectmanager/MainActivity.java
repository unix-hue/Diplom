package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Получаем доступ к компонентам активности
        Button button = findViewById(R.id.mail_button);
        EditText editText = findViewById(R.id.mail_edit);

        //При нажатии кнопки запускается этот код
        button.setOnClickListener(v -> {

            if (editText.getText().length() != 0) {

                //Если данные введены, то переходит на другую активность
                Intent intent = new Intent(MainActivity.this, RegActivity.class);
                startActivity(intent);
            }else{

                //Если данные не введены - вывод сообщения
                Toast toast = Toast.makeText(getApplicationContext(), "Введите логин!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}