package com.example.visionsharp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Book extends AppCompatActivity {
    private static final String TAG = "Book";


    TextView docName,date;
    EditText phone;


    Button editDoc,addDoc;
    private DatePickerDialog.OnDateSetListener DataSetListner1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent i = getIntent();
        final String docName1 = i.getStringExtra("DOC_NAME");


        final String PassedUnaname = i.getStringExtra("USER_NAME");
        final String PassedEmail = i.getStringExtra("USER_EMAIL");
        final String PassedPhone = i.getStringExtra("USER_PHONE");


        docName = findViewById(R.id.docName);
        date = findViewById(R.id.pDate);
        phone = findViewById(R.id.phone);

        addDoc = findViewById(R.id.btnBookDoc);
        editDoc = findViewById(R.id.btneditDoc);

        docName.setText(docName1);

        editDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Book.this, EditBook.class);
                in.putExtra("USER_NAME",PassedUnaname);
                in.putExtra("USER_EMAIL",PassedEmail);
                in.putExtra("USER_PHONE",PassedPhone);
                startActivity(in);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Book.this,
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

                String date1 = month + "/" + day + "/" + year;
                date.setText(date1);
            }
        };


        addDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(date.getText())){
                    date.setError("Enter Date!");
                    date.requestFocus();}
                else {

                    BookDB book = new BookDB(getApplicationContext());
                    long newID = book.addInfo(date.getText().toString(), docName.getText().toString(), phone.getText().toString());
                    Toast.makeText(Book.this, "Reservation Successfully......!    Reservation ID: " + newID, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), Payment.class);
                    i.putExtra("USER_NAME",PassedUnaname);
                    i.putExtra("USER_EMAIL",PassedEmail);
                    i.putExtra("USER_PHONE",PassedPhone);
                    startActivity(i);
                    date.setText(null);
                    phone.setText(null);


                }
            }
        });

    }
}