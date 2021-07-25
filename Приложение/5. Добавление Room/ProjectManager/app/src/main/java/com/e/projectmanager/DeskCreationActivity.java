package com.e.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.projectmanager.dao.Query;
import com.e.projectmanager.dao.models.Desk;

public class DeskCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_creation);

        Button button = findViewById(R.id.button5);
        EditText desk_edit = findViewById(R.id.desk_name);

        button.setOnClickListener(v -> {
            if (desk_edit.getText().length() != 0) {
                Desk desk = new Desk();
                desk.setUser_id(Query.getCurrent_user());
                desk.setName(desk_edit.getText().toString());

                Thread thread = new Thread() {
                    @Override
                    synchronized public void run(){
                        super.run();
                        Query.getAccess().addDesk(desk);
                    }
                };
                thread.start();

                this.finish();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Введите название доски!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}