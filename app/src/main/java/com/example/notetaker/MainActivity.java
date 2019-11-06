package com.example.notetaker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
    String recieveText;

    List<Note> noteList;
    ArrayAdapter<Note> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        MyGridLayout myGridLayout = new MyGridLayout(this);
        setContentView(myGridLayout);

        Button button = (Button) findViewById(R.id.addButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            }
        });

//        ListView listView = new ListView(this);
////        setContentView(listView);
//        List<String> userString = new ArrayList<>();
//        userString.add("hello");
////        userString.add(recieveText);
////        Toast.makeText(this, "here: " + recieveText, Toast.LENGTH_LONG).show();
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                userString
//        );
//        listView.setAdapter(arrayAdapter);

        noteList = new ArrayList<Note>();
//        noteList.add(new Note("woah", "hello"));
        ListView listView = (ListView) findViewById(R.id.listView);
        //        Toast.makeText(this, "here: " + recieveText, Toast.LENGTH_LONG).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });

        arrayAdapter = new ArrayAdapter<Note>(this,
                android.R.layout.simple_list_item_1,
                noteList
        );
        listView.setAdapter(arrayAdapter);
//        arrayAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK){

            recieveText = data.getStringExtra("titleEditText");
            Toast.makeText(this,recieveText + "", Toast.LENGTH_LONG).show();
            noteList.add(new Note(recieveText, "hello"));
            arrayAdapter.notifyDataSetChanged();

//            noteList = new ArrayList<Note>();
//            noteList.add(new Note(recieveText, "hello"));
//            ListView listView = (ListView) findViewById(R.id.listView);
//            //        Toast.makeText(this, "here: " + recieveText, Toast.LENGTH_LONG).show();
//            ArrayAdapter<Note> arrayAdapter = new ArrayAdapter<Note>(this,
//                    android.R.layout.simple_list_item_1,
//                    noteList
//            );
//            listView.setAdapter(arrayAdapter);
//            arrayAdapter.notifyDataSetChanged();
        }
    }
}
