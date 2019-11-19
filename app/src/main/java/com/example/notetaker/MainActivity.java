package com.example.notetaker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
//        MyGridLayout layout = new MyGridLayout(this);
//        setContentView(layout);

        noteList = new ArrayList<>();
        //Button createNewNote = findViewById(R.id.addNoteButton);
        final ListView notes = new ListView(this);
        setContentView(notes);

        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                noteList
        );
        notes.setAdapter(arrayAdapter);

        // enable multiple selection on list view
        notes.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // set the listener for entering CAM (contextual action mode)
        // user long presses they can select multiple items
        notes.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                int numChecked = notes.getCheckedItemCount();
                actionMode.setTitle(numChecked + " selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                // In onCreateActionMode(), inflate your CAM menu and return true
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.cam_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                // do not need for PA7
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                // task: determine which menu item was clicked
                // show toast
                // challenge: try to display the indexes of the selected items
                switch (menuItem.getItemId()) {
                    case R.id.deleteItem:
                        // not necessarily for PA7...
                        String temp = notes.getCheckedItemPositions().toString();
                        Toast.makeText(MainActivity.this, temp, Toast.LENGTH_SHORT).show();
                        actionMode.finish(); // exit CAM
                        return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                // do not need for PA7
            }
        });
//        createNewNote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
//                intent.putExtra("note", new Note());
//                intent.putExtra("index", -1);
//                startActivityForResult(intent, LOGIN_REQUEST_CODE);
//            }
//        });

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

    private void startEditItemActivity() {
//        Intent intent2 = new Intent(this, EditItemActivity.class);
//        startActivity(intent2);
        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
        intent.putExtra("note", new Note());
        intent.putExtra("index", -1);
        startActivityForResult(intent, LOGIN_REQUEST_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // override a callback that executes when the user presses a menu action

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.addMenuItem:
                startEditItemActivity();
                return true; // we consumed/handled the event
            // task: finish the two other cases, show toast messages
            case R.id.deleteItem:
                //still needs to be completed
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
