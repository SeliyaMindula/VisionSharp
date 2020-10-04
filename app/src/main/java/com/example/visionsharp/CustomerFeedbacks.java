package com.example.visionsharp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerFeedbacks extends AppCompatActivity {

    FeedbacksDatabase fedDb;
    private Spinner sp3, sp4;
    Button fAdd,fView,fEdit,fDel,edit;
    EditText other,editId;

    String PassedUnaname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_feedbacks);

        fedDb = new FeedbacksDatabase(this);
        sp3 = (Spinner) findViewById(R.id.spinner3);
        sp4 = (Spinner) findViewById(R.id.spinner4);
        other = (EditText)findViewById(R.id.fedOther);

        edit = (Button) findViewById(R.id.btnfedEdit);
        fAdd = (Button) findViewById(R.id.btnfedAdd);
        fView = (Button) findViewById(R.id.btnFedView);
        fEdit = (Button) findViewById(R.id.fededitbtn);
        fDel = (Button) findViewById(R.id.btnfedDel);
        editId= (EditText)findViewById(R.id.btnfededit);



        Intent i = getIntent();

        PassedUnaname = i.getStringExtra("USER_NAME");
        final String PassedEmail = i.getStringExtra("USER_EMAIL");
        final String PassedPhone = i.getStringExtra("USER_PHONE");


        AddData();
        ViewAll();
        UpdateData();
        DeleteData();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(CustomerFeedbacks.this, UpdateFeedback.class);
                continueIntent.putExtra("USER_NAME",PassedUnaname);
                continueIntent.putExtra("USER_EMAIL",PassedEmail);
                continueIntent.putExtra("USER_PHONE",PassedPhone);
                startActivity(continueIntent);
            }
        });

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CustomerFeedbacks.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.feedback));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(myAdapter);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(CustomerFeedbacks.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.rating));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp4.setAdapter(myAdapter1);

    }

    public void AddData(){
        fAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        boolean isInserted = fedDb.inserData(sp3.getSelectedItem().toString(),
                                sp4.getSelectedItem().toString(),
                                other.getText().toString(),
                                PassedUnaname);

                        if(isInserted = true)
                            Toast.makeText(CustomerFeedbacks.this, "New Record Inserted !", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CustomerFeedbacks.this, "Data not Inserted !", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void ViewAll(){
        fView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Cursor res = fedDb.getAllData(PassedUnaname);
                        if(res.getCount() == 0){
                            showMessage("Error!", "There is no Feedback");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Feedback :"+ res.getString(1)+"\n");
                            buffer.append("Rating :"+ res.getString(2)+"\n");
                            buffer.append("Other :"+ res.getString(3)+"\n\n");
                        }

                        showMessage("Feedback",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateData(){
        fEdit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = fedDb.updateData(editId.getText().toString(),sp3.getSelectedItem().toString());
                        if(isUpdated == true)
                            Toast.makeText(CustomerFeedbacks.this, "Your Feedback Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CustomerFeedbacks.this, "Data not Updated!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void DeleteData(){
        fDel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = fedDb.deleteData(editId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(CustomerFeedbacks.this, "Your feedback deleted!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CustomerFeedbacks.this, "Data not Deleted!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

}