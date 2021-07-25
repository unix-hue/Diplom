package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.projectmanager.dao.Query;
import com.e.projectmanager.dao.models.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.mail_button);
        EditText editText = findViewById(R.id.mail_edit);

        Query.create(getApplicationContext());
        Thread thread = new Thread() {
            @Override
            synchronized public void run(){
                super.run();
                userList = Query.getAccess().getUsersEmails();
            }
        };
        thread.start();

        button.setOnClickListener(v -> {
            Intent intent;

            if (isEmail(editText.getText().toString())) {
                boolean user_exist = false;
                String email = editText.getText().toString();

                for (int i = 0; i < userList.size(); i++) {
                    if(email.equals(userList.get(i).getEmail())) {
                        user_exist = true;
                        Query.setCurrent_user(userList.get(i).getId());
                        break;
                    }
                }

                if (user_exist) {
                    intent = new Intent(MainActivity.this, AuthActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, RegActivity.class);
                    intent.putExtra("email", editText.getText().toString());
                }

                startActivity(intent);
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Введено некорректное значение!", Toast.LENGTH_SHORT);
                toast.show();
            }

        });
    }

    private boolean isEmail(String text){
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}