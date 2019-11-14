/**
 * This program computes an app that allows the user to take, edit, and delete notes
 * CPSC 312-01, Fall 2019
 * Programming Assignment #6
 * No sources to cite.
 *
 * @author Joseph Torii, Eric Av
 * @version v1.0 11/6/19
 */

package com.example.notetaker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int LOGIN_REQUEST_CODE = 1;
    private ArrayAdapter<Note> arrayAdapter;
    private List<Note> noteList;
    private Note newNote;
    private int index;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            newNote = (Note)data.getSerializableExtra("note");
            index = data.getIntExtra("index", -1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (newNote != null) {
            if (index == -1) {
                noteList.add(newNote);
                arrayAdapter.notifyDataSetChanged();
            } else {
                noteList.set(index, newNote);
                arrayAdapter.notifyDataSetChanged();
            }
            newNote = null;
            index = -1;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyGridLayout layout = new MyGridLayout(this);
        setContentView(layout);

        noteList = new ArrayList<>();
        Button createNewNote = findViewById(R.id.addNoteButton);
        ListView notes = findViewById(R.id.notesListView);
        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                noteList
        );
        notes.setAdapter(arrayAdapter);

        createNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("note", new Note());
                intent.putExtra("index", -1);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            }
        });

        notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("note", noteList.get(i));
                intent.putExtra("index", i);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            }
        });

        notes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                final int deleteIndex = i;
                alertBuilder.setTitle("Delete a Note")
                        .setMessage("Are you sure you want to delete your " + adapterView.getItemAtPosition(i) + " note?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                noteList.remove(deleteIndex);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
                return true;
            }
        });
    }
}
