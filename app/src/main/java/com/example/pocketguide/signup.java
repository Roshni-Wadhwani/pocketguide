package com.example.pocketguide;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;



public class signup extends AppCompatActivity {

    // creating variables for our edit text and buttons.
    private EditText userNameEdt, passwordEdt;
    private Button sbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // initializing our edit text and buttons.
        userNameEdt = findViewById(R.id.idEdtUserName);
        passwordEdt = findViewById(R.id.idEdtPassword);
        sbtn = findViewById(R.id.idBtnRegister);
        final TextView login=(TextView) findViewById(R.id.idacclogin);


        // adding on click listener for our button.
        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDataEntered()){
                    Toast.makeText(getApplicationContext(),"Account created Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(signup.this,loginapp.class);
                    startActivity(intent);
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(signup.this,loginapp.class);
                startActivity(i);
            }

        });
    }


        boolean isEmpty(EditText text) {
            CharSequence str = text.getText().toString();
            return TextUtils.isEmpty(str);
        }


        boolean checkDataEntered() {
            if (isEmpty(userNameEdt)) {
                Toast t = Toast.makeText(this, "You must enter username to register!", Toast.LENGTH_SHORT);
                t.show();

            }

            if (isEmpty(passwordEdt)) {
                passwordEdt.setError("Password is required!");

            }



            return !isEmpty(userNameEdt) && !isEmpty(passwordEdt) ;
        }
    }


