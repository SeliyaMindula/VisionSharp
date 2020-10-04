package com.example.visionsharp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {

    EditText holdername, cardnum, exdate,cvvnum ;
    Button add, update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        holdername = findViewById(R.id.holdername);
        cardnum = findViewById(R.id.crdno);
        exdate = findViewById(R.id.exdate);
        cvvnum =  findViewById(R.id.cvvno);
        add = findViewById(R.id.btnAddPay);
        update = findViewById(R.id.btnUpdate);

        Intent i = getIntent();

        final String PassedUnaname = i.getStringExtra("USER_NAME");
        final String PassedEmail = i.getStringExtra("USER_EMAIL");
        final String PassedPhone = i.getStringExtra("USER_PHONE");



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(holdername.getText())){
                    holdername.setError("Please Enter Holder Name");
                    holdername.requestFocus();
                }
                else if(TextUtils.isEmpty(cardnum.getText())){
                    cardnum.setError("Please Enter Card Number");
                    cardnum.requestFocus();
                }
                else if(TextUtils.isEmpty(exdate.getText())){
                    exdate.setError("Please Enter Expire Date");
                    exdate.requestFocus();
                }
                else if(TextUtils.isEmpty(cvvnum.getText())){
                    cvvnum.setError("Please Enter cvv Number");
                    cvvnum.requestFocus();
                }else {

                    PDBHandler pdbHandler = new PDBHandler(getApplicationContext());
                    long newID = pdbHandler.addInfo(holdername.getText().toString(), cardnum.getText().toString(), exdate.getText().toString(), cvvnum.getText().toString());
                    Toast.makeText(Payment.this, "Details Added. Payment Details ID: " + newID, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(),user_dashbord.class);
                    i.putExtra("USER_NAME",PassedUnaname);
                    i.putExtra("USER_EMAIL",PassedEmail);
                    i.putExtra("USER_PHONE",PassedPhone);
                    startActivity(i);
                    holdername.setText(null);
                    cardnum.setText(null);
                    exdate.setText(null);
                    cvvnum.setText(null);
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditPayment.class);
                i.putExtra("USER_NAME",PassedUnaname);
                i.putExtra("USER_EMAIL",PassedEmail);
                i.putExtra("USER_PHONE",PassedPhone);
                startActivity(i);
                holdername.setText(null);
                cardnum.setText(null);
                exdate.setText(null);
                cvvnum.setText(null);
            }
        });

    }
}