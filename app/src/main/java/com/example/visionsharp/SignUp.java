package com.example.visionsharp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    private Button sign;
    private EditText username,email,phone,pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        sign = findViewById(R.id.btnsubCAc);

        username = findViewById(R.id.usertxt);
        email = findViewById(R.id.emailInput);
        phone = findViewById(R.id.phoneSign);
        pwd = findViewById(R.id.pwd);




        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText())){
                    username.setError("Please Enter User Name");
                    username.requestFocus();
                }
                else if(TextUtils.isEmpty(email.getText())){
                    email.setError("Please Enter Email");
                    email.requestFocus();
                }
                else if(TextUtils.isEmpty(phone.getText())){
                    phone.setError("Please Enter Phone Number");
                    phone.requestFocus();
                }
                else if(TextUtils.isEmpty(pwd.getText())){
                    pwd.setError("Please Enter Password");
                    pwd.requestFocus();
                }
                else {
                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    long newID = dbHandler.addInfoUsers(username.getText().toString(), email.getText().toString(), phone.getText().toString(), pwd.getText().toString());
                    Toast.makeText(SignUp.this, "User Added. User ID: " + newID, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(),LoginPage.class);
                    startActivity(i);


                }
            }
        });

    }


}