package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.projectmanager.dao.Query;
import com.e.projectmanager.dao.models.User;

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

                Thread thread = new Thread() {
                    @Override
                    synchronized public void run(){
                        super.run();
                        Query.getAccess().addUser(user);
                        Query.setCurrent_user(Query.getAccess().getId());
                    }
                };
                thread.start();

                startActivity(intent);
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Заполните все поля!", Toast.LENGTH_SHORT);
                toast.show();
            }

        });
    }
}