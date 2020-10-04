package com.example.visionsharp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class repairSpec extends AppCompatActivity {
    private static final String TAG = "Repair";

    Spinner type;
    EditText phone;

    Button editSpec,addSpec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_spec);

        Intent i = getIntent();
        final String docName1 = i.getStringExtra("DOC_NAME");


        final String PassedUnaname = i.getStringExtra("USER_NAME");
        final String PassedEmail = i.getStringExtra("USER_EMAIL");
        final String PassedPhone = i.getStringExtra("USER_PHONE");


        type = findViewById(R.id.type);
        phone = findViewById(R.id.phone);

        editSpec = findViewById(R.id.btneditSpec);
        addSpec = findViewById(R.id.btnAddRep);


        editSpec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(repairSpec.this, EditSpecs.class);
                in.putExtra("USER_NAME",PassedUnaname);
                in.putExtra("USER_EMAIL",PassedEmail);
                in.putExtra("USER_PHONE",PassedPhone);
                startActivity(in);
            }
        });




        addSpec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(phone.getText())){
                    phone.setError("Enter Phone Number!");
                    phone.requestFocus();}
                else {

                    DBRepair phoneCheck = new DBRepair(getApplicationContext());
                    List phoneCh = phoneCheck.readAllSpecs(phone.getText().toString());
                    if (phoneCh.isEmpty())
                    {
                        DBRepair reserve = new DBRepair(getApplicationContext());
                        long newID = reserve.makeReserve(type.getSelectedItem().toString(), phone.getText().toString());
                        Toast.makeText(repairSpec.this, "You Have Reserved a Repair Successfully..!!    Reservation ID: " + newID, Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(), user_dashbord.class);
                        i.putExtra("USER_NAME",PassedUnaname);
                        i.putExtra("USER_EMAIL",PassedEmail);
                        i.putExtra("USER_PHONE",PassedPhone);
                        startActivity(i);
                        phone.setText(null);
                    }
                    else{
                        Toast.makeText(repairSpec.this, "Another Reservation is Have From Enterd Number", Toast.LENGTH_SHORT).show();
                    }





                }
            }
        });

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(repairSpec.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.specs));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(myAdapter);


    }

    }
