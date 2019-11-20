package com.example.notetaker;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.ListView;

public class MyGridLayout extends GridLayout {

    public MyGridLayout(final Context context){
        super(context);

        ListView notesListView;
        // Button newNoteButton;
        setColumnCount(1);

        GridLayout.LayoutParams notesListLayout = new GridLayout.LayoutParams();
        notesListLayout.rowSpec = GridLayout.spec(0, 1, 1);
        notesListLayout.columnSpec = GridLayout.spec(0, 1, 1);

        notesListView = new ListView(context);
        notesListView.setId(R.id.notesListView);
        //notesListLayout.setGravity(Gravity.TOP);
        notesListView.setLayoutParams(notesListLayout);
        this.addView(notesListView);
    }
}
