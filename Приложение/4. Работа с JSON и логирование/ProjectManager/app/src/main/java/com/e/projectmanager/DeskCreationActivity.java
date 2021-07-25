package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.projectmanager.models.Desk;

public class DeskCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_creation);

        Button button = findViewById(R.id.button5);
        EditText desk = findViewById(R.id.desk_name);

        button.setOnClickListener(v -> {
            if (desk.getText().length() != 0) {
                DeskActivity.editList(new Desk(desk.getText().toString()));
                this.finish();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Введите название доски!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}