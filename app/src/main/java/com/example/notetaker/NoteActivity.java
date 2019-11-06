package com.example.notetaker;

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


    static final int LOGIN_REQUEST_CODE = 1;
    String[] choice = {"Personal", "School", "Work", "Other"};
    String titleInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        MyGridLayout2 myGridLayout2 = new MyGridLayout2(this);
        setContentView(myGridLayout2);

        Intent intent = getIntent();

        final EditText titleEditText = (EditText) findViewById(R.id.titleEditText);
//        titleInput = titleEditText.getText().toString();

//        Note note = new Note(titleInput, "hello");

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,choice);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        Button button = (Button) findViewById(R.id.doneButton);
        button.setText("Done");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("titleEditText", titleInput);
                setResult(Activity.RESULT_OK, intent);
                titleInput = titleEditText.getText().toString();
                Toast.makeText(NoteActivity.this, "" + titleInput, Toast.LENGTH_SHORT).show();
//                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){}


}
