package com.example.visionsharp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Available extends AppCompatActivity {

    TextView docName,special;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);

        Button book = findViewById(R.id.bookDoc);
        ImageView imgDoc = findViewById(R.id.imageDoc);
        docName = findViewById(R.id.docName);
        special =findViewById(R.id.special);

        String no = getIntent().getStringExtra("val");
        int num = Integer.parseInt(no);

        Intent i = getIntent();

        final String PassedUnaname = i.getStringExtra("USER_NAME");
        final String PassedEmail = i.getStringExtra("USER_EMAIL");
        final String PassedPhone = i.getStringExtra("USER_PHONE");

        if (num == 1){
            imgDoc.setImageResource(R.drawable.pict01);
            docName.setText("Hana");
            special.setText("Dentist");

        }
        if (num == 2){
            imgDoc.setImageResource(R.drawable.pict02);
            docName.setText("Ulrich");
            special.setText("Surgent");

        }
        if (num == 3){
            imgDoc.setImageResource(R.drawable.pict03);
            docName.setText("Jonas");
            special.setText("Pediatricians");

        }
        if (num == 4){
            imgDoc.setImageResource(R.drawable.pict04);
            docName.setText("Jonas");
            special.setText("Ophthalmologists");

        }
        if (num == 5){
            imgDoc.setImageResource(R.drawable.pict05);
            docName.setText("Ulrich");
            special.setText("Surgent");

        }
        if (num == 6){
            imgDoc.setImageResource(R.drawable.pict06);
            docName.setText("Hana");
            special.setText("Dentist");

        }

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Available.this, Book.class);
                i.putExtra("DOC_NAME",docName.getText().toString());
                i.putExtra("USER_NAME",PassedUnaname);
                i.putExtra("USER_EMAIL",PassedEmail);
                i.putExtra("USER_PHONE",PassedPhone);
                startActivity(i);
            }
        });

    }
}