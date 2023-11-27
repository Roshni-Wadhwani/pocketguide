package com.example.pocketguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.os.*;



public class firstpageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        new CountDownTimer(1000,500){
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                startActivity(new Intent(firstpageActivity.this,user.class));
                firstpageActivity.this.finish();
            }
        }.start();
    }



}