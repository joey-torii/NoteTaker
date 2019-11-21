package com.example.notetaker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int LOGIN_REQUEST_CODE = 1;
    private Note newNote;
    private int index;
    final NoteOpenHelper openHelper = new NoteOpenHelper(this);
    SimpleCursorAdapter cursorAdapter;

    //Pre: Second Activity finishes
    //Post: Edit a note and add a note function returns
    //Function: To return a saved note state and edit a note by saving it as well
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            newNote = (Note)data.getSerializableExtra("note");
            index = data.getIntExtra("index", -1);

            if(index > -1) {
                openHelper.updateNoteById(index, newNote);
                Cursor cursor = openHelper.getAllNotes();
                cursorAdapter.changeCursor(cursor);
            }
            else {
                openHelper.insertNote(newNote);
                Cursor cursor = openHelper.getAllNotes();
                cursorAdapter.changeCursor(cursor);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (newNote != null) {
            if (index == -1) {
                Cursor cursor =  openHelper.getSelectAllNotesCursor();
                cursorAdapter.changeCursor(cursor);
            } else {
                Cursor cursor =  openHelper.getSelectAllNotesCursor();
                cursorAdapter.changeCursor(cursor);
            }
            newNote = null;
            index = -1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ListView notes = new ListView(this);
        setContentView(notes);
        final NoteOpenHelper openHelper = new NoteOpenHelper(this);

        cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_activated_1,
                openHelper.getSelectAllNotesCursor(),
                new String[] {NoteOpenHelper.TITLE},
                new int[] {android.R.id.text1},
                0);

        notes.setAdapter(cursorAdapter);
        notes.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        notes.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                int numChecked = notes.getCheckedItemCount();
                actionMode.setTitle(numChecked + " selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.cam_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.deleteMenuItem:
                        NoteOpenHelper openHelper = new NoteOpenHelper(MainActivity.this);
                        SparseBooleanArray checkedItemPositions = notes.getCheckedItemPositions();
                        for(int i = 0; i < checkedItemPositions.size(); i++)
                        {
                            if(checkedItemPositions.valueAt(i))
                            {
                                int id = (int) cursorAdapter.getItemId(checkedItemPositions.keyAt(i));
                                openHelper.deleteIndividualNote(id);
                            }
                        }
                        Cursor cursor = openHelper.getSelectAllNotesCursor();
                        cursorAdapter.changeCursor(cursor);
                        actionMode.finish();
                        return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
            }
        });

        notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                int id2 = (int) cursorAdapter.getItemId(position);
                int id3 = (int) id;
                Note note = openHelper.getNoteById(id2);
                intent.putExtra("note", note);
                intent.putExtra("id", id3);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.addMenuItem:
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("note", new Note());
                intent.putExtra("index", -1);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
                return true;
            case R.id.deleteItem:
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setTitle("Delete All Notes")
                        .setMessage("Are you sure you want to delete all notes?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                openHelper.deleteAllNotes();
                                Cursor cursor = openHelper.getAllNotes();
                                cursorAdapter.changeCursor(cursor);
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}