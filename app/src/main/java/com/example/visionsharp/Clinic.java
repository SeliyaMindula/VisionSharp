package com.example.visionsharp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Clinic extends AppCompatActivity {

    ImageView image1, image2, image3, image4, image5, image6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);



        Intent i = getIntent();

        final String PassedUnaname = i.getStringExtra("USER_NAME");
        final String PassedEmail = i.getStringExtra("USER_EMAIL");
        final String PassedPhone = i.getStringExtra("USER_PHONE");


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent continueIntent = new Intent(Clinic.this, Available.class);
                continueIntent.putExtra("val", "1");
                continueIntent.putExtra("USER_NAME",PassedUnaname);
                continueIntent.putExtra("USER_EMAIL",PassedEmail);
                continueIntent.putExtra("USER_PHONE",PassedPhone);
                startActivity(continueIntent);
            }
        });


        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent continueIntent = new Intent(Clinic.this, Available.class);
                continueIntent.putExtra("val", "2");
                continueIntent.putExtra("USER_NAME",PassedUnaname);
                continueIntent.putExtra("USER_EMAIL",PassedEmail);
                continueIntent.putExtra("USER_PHONE",PassedPhone);
                startActivity(continueIntent);
            }
        });


        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent continueIntent = new Intent(Clinic.this, Available.class);
                continueIntent.putExtra("val", "3");
                continueIntent.putExtra("USER_NAME",PassedUnaname);
                continueIntent.putExtra("USER_EMAIL",PassedEmail);
                continueIntent.putExtra("USER_PHONE",PassedPhone);
                startActivity(continueIntent);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent continueIntent = new Intent(Clinic.this, Available.class);
                continueIntent.putExtra("val", "4");
                continueIntent.putExtra("USER_NAME",PassedUnaname);
                continueIntent.putExtra("USER_EMAIL",PassedEmail);
                continueIntent.putExtra("USER_PHONE",PassedPhone);
                startActivity(continueIntent);
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent continueIntent = new Intent(Clinic.this, Available.class);
                continueIntent.putExtra("val", "5");
                continueIntent.putExtra("USER_NAME",PassedUnaname);
                continueIntent.putExtra("USER_EMAIL",PassedEmail);
                continueIntent.putExtra("USER_PHONE",PassedPhone);
                startActivity(continueIntent);
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent continueIntent = new Intent(Clinic.this, Available.class);
                continueIntent.putExtra("val", "5");
                continueIntent.putExtra("USER_NAME",PassedUnaname);
                continueIntent.putExtra("USER_EMAIL",PassedEmail);
                continueIntent.putExtra("USER_PHONE",PassedPhone);
                startActivity(continueIntent);
            }
        });
    }
}