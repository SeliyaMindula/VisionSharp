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

public class EditSpecs extends AppCompatActivity {

    private static final String TAG = "EditSpecs";

    private EditText phone;
    private Spinner type;
    private Button search,delete,edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_specs);

        phone =findViewById(R.id.editphone);
        type =findViewById(R.id.type);
        search =findViewById(R.id.btnsearch);
        delete =findViewById(R.id.btndel);
        edit =findViewById(R.id.btnsubmitSpecs);


        Intent i = getIntent();

        final String PassedUnaname = i.getStringExtra("USER_NAME");
        final String PassedEmail = i.getStringExtra("USER_EMAIL");
        final String PassedPhone = i.getStringExtra("USER_PHONE");

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(EditSpecs.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.specs));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(myAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBRepair rep = new DBRepair(getApplicationContext());
                List phonenumber = rep.readAllSpecs(phone.getText().toString());

                if (phonenumber.isEmpty()){
                    Toast.makeText(EditSpecs.this, "No Reservation Phone Number Empty", Toast.LENGTH_SHORT).show();
                    phone.setText(null);
                }
                else {

                    Toast.makeText(EditSpecs.this, "Reservation Details Found.....! Mobile Number: "+phonenumber.get(1).toString(), Toast.LENGTH_SHORT).show();
                    phone.setText(phonenumber.get(1).toString());
                    type.setSelection(myAdapter.getPosition(phonenumber.get(0).toString()));;

                }

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(phone.getText())) {
                    phone.setError("Please Search your Phone Number!");
                    phone.requestFocus();
                } else {

                    DBRepair dbRep = new DBRepair(getApplicationContext());

                    Boolean status = dbRep.specUpdate(type.getSelectedItem().toString(),phone.getText().toString());
                    if (status) {
                        Toast.makeText(EditSpecs.this, "Reservation Details Updated", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), user_dashbord.class);
                        i.putExtra("USER_NAME",PassedUnaname);
                        i.putExtra("USER_EMAIL",PassedEmail);
                        i.putExtra("USER_PHONE",PassedPhone);
                        startActivity(i);
                    } else {
                        Toast.makeText(EditSpecs.this, "Repair Update Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(phone.getText())){
                    phone.setError("Please Search your Phone Number!");
                    phone.requestFocus();}
                else {

                    DBRepair repDB = new DBRepair(getApplicationContext());
                    repDB.specDelete(phone.getText().toString());


                    Toast.makeText(EditSpecs.this, "Repair Details Deleted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), user_dashbord.class);
                    i.putExtra("USER_NAME",PassedUnaname);
                    i.putExtra("USER_EMAIL",PassedEmail);
                    i.putExtra("USER_PHONE",PassedPhone);
                    startActivity(i);

                    phone.setText(null);
                    type.getSelectedItem();

                }
            }
        });
    }
}