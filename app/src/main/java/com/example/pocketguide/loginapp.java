package com.example.pocketguide;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.*;
import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.android.material.button.MaterialButton;

public class loginapp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginapp);

        TextView Username = (TextView) findViewById(R.id.username);
        TextView Password = (TextView) findViewById(R.id.password);


        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //incorrect
                if (Username.getText().toString().equals("admin") && Password.getText().toString().equals("admin")) {
                    //correct
                    Toast.makeText(loginapp.this, " ADMIN LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(loginapp.this,mainpage.class);
                    startActivity(i);

                } else if(Username.getText().toString().equals("user") && Password.getText().toString().equals("user")){
                    Toast.makeText(loginapp.this, " USER LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(loginapp.this,usermain.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(loginapp.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}