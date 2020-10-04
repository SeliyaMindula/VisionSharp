package com.example.visionsharp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    EditText username,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username = findViewById(R.id.uname);
        password = findViewById(R.id.upassword);
        login = findViewById(R.id.btnlogin);



        TextView frpassword = (TextView) findViewById(R.id.fpassword);

        frpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(LoginPage.this, EditUser.class);
                startActivity(continueIntent);
            }
        });

        login = findViewById(R.id.btnlogin);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                if(dbHandler.LoginUser(username.getText().toString(),password.getText().toString())){

                    Toast.makeText(LoginPage.this, "Logged in Success !", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), user_dashbord.class);
                    i.putExtra("USER_NAME",username.getText().toString());

                    username.setText(null);
                    password.setText(null);

                    startActivity(i);
                }
            }
        });
    }
}