package com.example.visionsharp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EditPayment extends AppCompatActivity {

    EditText holdername, cardnum, exdate,cvvnum ;
    Button edit, delete, search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_payment);

        holdername = findViewById(R.id.edHolderName);
        cardnum = findViewById(R.id.edCnum);
        exdate = findViewById(R.id.edExdate);
        cvvnum =  findViewById(R.id.edCvnum);
        edit = findViewById(R.id.edBtnEdit);
        delete = findViewById(R.id.btnUpdate);
        search = findViewById(R.id.edBtnSch);

        Intent i = getIntent();

        final String PassedUnaname = i.getStringExtra("USER_NAME");
        final String PassedEmail = i.getStringExtra("USER_EMAIL");
        final String PassedPhone = i.getStringExtra("USER_PHONE");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PDBHandler pdbHandler = new PDBHandler(getApplicationContext());
                List hname = pdbHandler.readAllInfo(holdername.getText().toString());

                if (hname.isEmpty()){
                    Toast.makeText(EditPayment.this, "No Details. Please enter details..!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),Payment.class);
                    i.putExtra("USER_NAME",PassedUnaname);
                    i.putExtra("USER_EMAIL",PassedEmail);
                    i.putExtra("USER_PHONE",PassedPhone);
                    startActivity(i);
                    holdername.setText(null);
                }
                else {

                    Toast.makeText(EditPayment.this, "Details Found! Details: "+hname.get(0).toString(), Toast.LENGTH_SHORT).show();
                    holdername.setText(hname.get(0).toString());
                    cardnum.setText(hname.get(1).toString());
                    exdate.setText(hname.get(2).toString());
                    cvvnum.setText(hname.get(3).toString());
                }

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PDBHandler pdbHandler = new PDBHandler(getApplicationContext());

                Boolean status = pdbHandler.updateInfo(holdername.getText().toString(),cardnum.getText().toString(),exdate.getText().toString(),cvvnum.getText().toString());
                if (status){
                    Toast.makeText(EditPayment.this, "Details Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditPayment.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PDBHandler pdbHandler = new PDBHandler(getApplicationContext());
                pdbHandler.deleteInfo(holdername.getText().toString());

                Toast.makeText(EditPayment.this, "Payment Details Deleted", Toast.LENGTH_SHORT).show();

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
        });

    }
}