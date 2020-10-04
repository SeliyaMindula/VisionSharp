package com.example.visionsharp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;

public class EditBook extends AppCompatActivity {

    private static final String TAG = "EditBook";
    private TextView pDate;
    private EditText phne;
    private Spinner docs;
    private Button search,delete,edit;

    private DatePickerDialog.OnDateSetListener DataSetListner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        pDate =findViewById(R.id.pDate);
        phne =findViewById(R.id.editphone);
        docs =findViewById(R.id.doc);

        search =findViewById(R.id.btnsearch);
        delete =findViewById(R.id.btndel);
        edit =findViewById(R.id.btnsubmit);

        Intent i = getIntent();

        final String PassedUnaname = i.getStringExtra("USER_NAME");
        final String PassedEmail = i.getStringExtra("USER_EMAIL");
        final String PassedPhone = i.getStringExtra("USER_PHONE");

        pDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditBook.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DataSetListner1,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        DataSetListner1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Log.d(TAG, "OnDateSet:mm/dd/yyyy:" + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                pDate.setText(date);


            }
        };


        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(EditBook.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.docs));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        docs.setAdapter(myAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BookDB BookDB = new BookDB(getApplicationContext());
                List phonenumber = BookDB.readAllInfo(phne.getText().toString());

                if (phonenumber.isEmpty()){
                    Toast.makeText(EditBook.this, "No Reservation", Toast.LENGTH_SHORT).show();
                    phne.setText(null);
                }
                else {

                    Toast.makeText(EditBook.this, "Reservation Details Found.....!    Mobile Number: "+phonenumber.get(0).toString(), Toast.LENGTH_SHORT).show();
                    pDate.setText(phonenumber.get(0).toString());
                    phne.setText(phonenumber.get(2).toString());
                    docs.setSelection(myAdapter.getPosition(phonenumber.get(1).toString()));;

                }

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(phne.getText())) {
                    phne.setError("Please Search your Phone Number!");
                    phne.requestFocus();
                } else {

                    BookDB bookDB = new BookDB(getApplicationContext());

                    Boolean status = bookDB.updateInfo(pDate.getText().toString(), docs.getSelectedItem().toString(), phne.getText().toString());
                    if (status) {
                        Toast.makeText(EditBook.this, "Reservation Details Updated", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), Payment.class);
                        i.putExtra("USER_NAME",PassedUnaname);
                        i.putExtra("USER_EMAIL",PassedEmail);
                        i.putExtra("USER_PHONE",PassedPhone);
                        startActivity(i);
                    } else {
                        Toast.makeText(EditBook.this, "Reservation Update Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(phne.getText())){
                    phne.setError("Please Search your Phone Number!");
                    phne.requestFocus();}
                else {

                    BookDB bookDB = new BookDB(getApplicationContext());
                    bookDB.deleteInfo(phne.getText().toString());


                    Toast.makeText(EditBook.this, "Reservation Details Deleted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), user_dashbord.class);
                    i.putExtra("USER_NAME",PassedUnaname);
                    i.putExtra("USER_EMAIL",PassedEmail);
                    i.putExtra("USER_PHONE",PassedPhone);
                    startActivity(i);

                    phne.setText(null);
                    pDate.setText(null);
                    docs.getSelectedItem();

                }
            }
        });
    }
}