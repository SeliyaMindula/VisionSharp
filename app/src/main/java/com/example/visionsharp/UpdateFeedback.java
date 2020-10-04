package com.example.visionsharp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateFeedback extends AppCompatActivity {


    Button update,delete;
    EditText Id;
    TextView check;
    FeedbacksDatabase fedDb;
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_feedback);

        update=findViewById(R.id.fedUpdate);
        delete=findViewById(R.id.fedDelete);
        Id = findViewById(R.id.IdText);

        sp = findViewById(R.id.spinner);


        Intent i = getIntent();

        final String PassedUnaname = i.getStringExtra("USER_NAME");
        final String PassedEmail = i.getStringExtra("USER_EMAIL");
        final String PassedPhone = i.getStringExtra("USER_PHONE");


        FeedUpdate();
        FeedDelete();

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(UpdateFeedback.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.rating));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(myAdapter1);

    }

    private void FeedDelete() {
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FeedbacksDatabase feedbacksDatabase = new FeedbacksDatabase(getApplicationContext());

                        if(feedbacksDatabase.deleteData(Id.getText().toString())>0){
                            Toast.makeText(UpdateFeedback.this, "Your feedback deleted!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(UpdateFeedback.this, "Your feedback not deleted!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }

    private void FeedUpdate() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbacksDatabase feedbacksDatabase = new FeedbacksDatabase(getApplicationContext());

                if(feedbacksDatabase.updateData(Id.getText().toString(),sp.getSelectedItem().toString())){
                    Toast.makeText(UpdateFeedback.this, "Your feedback Updated!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UpdateFeedback.this, "Your feedback not Updated!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}