package com.example.pocketguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainpage extends AppCompatActivity {

    private Button generateQRBtn,scanQRBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        generateQRBtn = findViewById(R.id.generatebtn);
        scanQRBtn = findViewById(R.id.scanbtn);
        generateQRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mainpage.this,MainActivity.class);
                startActivity(i);
            }
        });

        scanQRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mainpage.this,QR_scanner.class);
                startActivity(i);
            }
        });
    }
}