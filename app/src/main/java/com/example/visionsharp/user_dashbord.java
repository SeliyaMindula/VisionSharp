package com.example.visionsharp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class user_dashbord extends AppCompatActivity {

    TextView UserDashName,UDashEmail;
    Button book,paymentDetails,feedback,deleteUser,UpdateUser,repair;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashbord);

        UserDashName = findViewById(R.id.DashUname);
        paymentDetails = findViewById(R.id.btnPayDetails);
        book = findViewById(R.id.btnBook);
        feedback = findViewById(R.id.feedback);
        deleteUser =findViewById(R.id.btnupdate);
        UpdateUser = findViewById(R.id.deleteUser);
        UDashEmail = findViewById(R.id.emailShowDash);
        repair = findViewById(R.id.repairbtn);

        final String emailDash,phoneNoDash;

        Intent i = getIntent();

        String Unaname = i.getStringExtra("USER_NAME");

        UserDashName.setText(Unaname);


        DBHandler dbHandler = new DBHandler(getApplicationContext());
        List users = dbHandler.readAllInfoUsers(Unaname);

        emailDash = users.get(1).toString();
        phoneNoDash = users.get(2).toString();

        UDashEmail.setText(emailDash);




        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Clinic.class);
                in.putExtra("USER_NAME",UserDashName.getText().toString());
                in.putExtra("USER_EMAIL",UDashEmail.getText().toString());
                in.putExtra("USER_PHONE",phoneNoDash);
                startActivity(in);
            }
        });


    }
}