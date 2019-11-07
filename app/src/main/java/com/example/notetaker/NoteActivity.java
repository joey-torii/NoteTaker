package com.example.notetaker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText titleEditText;
    EditText contentEditText;
    Spinner spinnerText;
    static final int LOGIN_REQUEST_CODE = 1;
    String[] choice = {"Personal", "School", "Work", "Other"};
    String titleInput;
    String contentInput;
    int spinnerInput;
    static final String TAG = "noteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        MyGridLayout2 myGridLayout2 = new MyGridLayout2(this);
        setContentView(myGridLayout2);

        titleEditText = (EditText) findViewById(R.id.titleEditText);
        contentEditText = (EditText) findViewById(R.id.content);
        spinnerText = (Spinner) findViewById(R.id.spinner);
        spinnerInput = spinnerText.getSelectedItemPosition();

        Intent intent2 = getIntent();
        if(intent2 != null)
        {
            titleInput = intent2.getStringExtra("titleText");
            Log.d(TAG, "onCreate: "+ titleInput);
            contentInput = intent2.getStringExtra("contentText");
            Log.d(TAG, "onCreate: "+ contentInput);
            spinnerInput = intent2.getIntExtra("spinnerText", 0);

            titleEditText.setText(titleInput);
            contentEditText.setText(contentInput);
            spinnerText.setSelection(spinnerInput);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, choice);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerText.setAdapter(adapter);
        spinnerText.setOnItemSelectedListener(this);

        Button button = (Button) findViewById(R.id.doneButton);
        button.setText("Done");
        try {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!titleEditText.getText().toString().isEmpty()) {
                        titleInput = titleEditText.getText().toString();
                        contentInput = contentEditText.getText().toString();
                        Intent intent = new Intent();
                        intent.putExtra("titleEditText", titleInput);
                        intent.putExtra("contentEditText", contentInput);
                        intent.putExtra("spinnerEditText", spinnerInput);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(NoteActivity.this, "Title is empty, try again" , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(NoteActivity.this, "Title is empty, try again" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){}
}




