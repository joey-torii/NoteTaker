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

public class NoteActivity extends AppCompatActivity {

    static final int PERSONAL = 0;
    static final int SCHOOL = 1;
    static final int WORK = 2;
    static final int OTHER = 3;

    private Note note;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyGridLayout2 myGridLayout2 = new MyGridLayout2(this);
        setContentView(myGridLayout2);
        final EditText noteTitle = findViewById(R.id.titleEditText);
        final EditText content = findViewById(R.id.contentEditText);
        final Spinner noteType = findViewById(R.id.spinnerType);
        index = -1;

        Intent intent = getIntent();
        if(intent != null){
            note = (Note)intent.getSerializableExtra("note");
            index = intent.getIntExtra("index", -1);
            noteTitle.setText(note.getTitle());
            content.setText(note.getContent());
        }

        noteTitle.setText(note.getTitle());
        content.setText(note.getContent());
        noteType.setSelection(getAdapterIndex());

        noteType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = adapterView.getItemAtPosition(i).toString();
                note.setType(selection);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note.setTitle(noteTitle.getText().toString());
                note.setContent(content.getText().toString());

                Intent intent = new Intent(NoteActivity.this, MainActivity.class);
                intent.putExtra("note", note);
                intent.putExtra("index", index);
                setResult(RESULT_OK, intent);
                NoteActivity.this.finish();
            }
        });
    }

    private int getAdapterIndex() {
        switch (note.getType()) {
            case "Other":
                return OTHER;
            case "School":
                return SCHOOL;
            case "Work":
                return WORK;
            default:
                return PERSONAL;
        }
    }
}



