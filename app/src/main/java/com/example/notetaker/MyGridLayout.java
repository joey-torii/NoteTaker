package com.example.notetaker;

import android.content.Context;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;

import java.util.List;

public class MyGridLayout extends GridLayout {

    public MyGridLayout(final Context context){
        super(context);

        setColumnCount(1);

        Button myButton = new Button(context);
        myButton.setText("ADD NEW NOTE");
        myButton.setId(R.id.addButton);
        GridLayout.Spec buttonRowSpec = GridLayout.spec(0, 1, 1);
        GridLayout.Spec buttonColSpec = GridLayout.spec(0,1,1);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(buttonRowSpec,buttonColSpec);
        myButton.setLayoutParams(layoutParams);
        addView(myButton);



        ListView listView = new ListView(context);
        GridLayout.Spec listViewRowSpec = GridLayout.spec(1, 1, 19);
        GridLayout.Spec listViewColSpec = GridLayout.spec(0,1,1);
        GridLayout.LayoutParams layoutParams1 = new GridLayout.LayoutParams(listViewRowSpec,listViewColSpec);
        listView.setLayoutParams(layoutParams1);
        listView.setId(R.id.listView);
        addView(listView);
    }
}
