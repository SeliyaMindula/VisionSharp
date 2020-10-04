package com.example.visionsharp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class EditUser extends AppCompatActivity {

    EditText username, userFName, userLName, userEmail, phone, password, city;
    Button delete, submit, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);


        userEmail = findViewById(R.id.emailInput);
        phone = findViewById(R.id.phoneinput);
        password = findViewById(R.id.passwordinput);

        delete = findViewById(R.id.btnDelete);
        submit = findViewById(R.id.btnSubmit);


        Intent i = getIntent();

        final String PassedUnaname = i.getStringExtra("USER_NAME");
        final String PassedEmail = i.getStringExtra("USER_EMAIL");
        final String PassedPhone = i.getStringExtra("USER_PHONE");


        DBHandler dbHandler = new DBHandler(getApplicationContext());
        List user = dbHandler.readAllInfoUsers(PassedUnaname);

        if (user.isEmpty()) {
            Toast.makeText(EditUser.this, "No User", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(EditUser.this, "User Found! User: " + user.get(0).toString(), Toast.LENGTH_SHORT).show();

            userEmail.setText(user.get(1).toString());
            phone.setText(user.get(2).toString());
            password.setText(user.get(3).toString());

        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                Boolean status = dbHandler.updateInfoUsers(PassedUnaname, userEmail.getText().toString(), phone.getText().toString(), password.getText().toString());
                if (status) {
                    Toast.makeText(EditUser.this, "User Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditUser.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }

                Intent i = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(i);

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteInfoUsers(PassedUnaname);

                Toast.makeText(EditUser.this, "User Deleted", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), SignUp.class);
                startActivity(i);


                userEmail.setText(null);
                phone.setText(null);
                password.setText(null);

            }
        });


    }
}