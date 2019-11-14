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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    String recieveText;
    String recieveContent;
    int recieveSpinner;

    List<Note> noteList;
    ArrayAdapter<Note> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        MyGridLayout myGridLayout = new MyGridLayout(this);
//        setContentView(myGridLayout);

//        Button button = (Button) findViewById(R.id.addButton);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
//                startActivityForResult(intent, LOGIN_REQUEST_CODE);
//            }
//        });

        final ListView listView = new ListView(this);
        setContentView(listView);

        noteList = new ArrayList<Note>();

//        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                recieveText = noteList.get(position).getTitle();
                recieveContent = noteList.get(position).getContent();

                intent.putExtra("titleText", recieveText);
                intent.putExtra("contentText", recieveContent);
                intent.putExtra("spinnerText", recieveSpinner);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);

                noteList.remove(position);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle( "Delete a Note" )
                        .setMessage("Are you sure you want to delete your " + recieveText + " note?")
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                dialoginterface.cancel();
                            }})
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                noteList.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        }).show();
                return true;
            }
        });

        arrayAdapter = new ArrayAdapter<Note>(this,
                android.R.layout.simple_list_item_1,
                noteList
        );
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK){

            recieveText = data.getStringExtra("titleEditText");
            recieveContent = data.getStringExtra("contentEditText");
            recieveSpinner = data.getIntExtra("spinnerEditText", 0);

            noteList.add(new Note(recieveText, recieveContent));
            arrayAdapter.notifyDataSetChanged();
        }
    }

    private void startEditItemActivity(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
