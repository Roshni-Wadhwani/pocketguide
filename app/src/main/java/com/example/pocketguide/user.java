package com.example.pocketguide;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.*;
import android.content.*;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class user extends AppCompatActivity {

    private Button userbtn;
    private Button adminbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        userbtn = findViewById(R.id.btn1);
        adminbtn=findViewById(R.id.btn);

        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(user.this,signup.class);
                startActivity(intent);
            }
        });

        adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(user.this,signup.class);
                startActivity(intent);
            }
        });
    }


}