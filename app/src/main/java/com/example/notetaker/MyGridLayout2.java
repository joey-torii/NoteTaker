package com.example.notetaker;

import android.content.Context;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

public class MyGridLayout2 extends GridLayout {

    public MyGridLayout2(final Context context){
        super(context);
        setColumnCount(3);

        GridLayout.LayoutParams layoutParams = new LayoutParams();
        layoutParams.rowSpec = GridLayout.spec(1, 1, 1);
        layoutParams.columnSpec = GridLayout.spec(0, 3, 1);


        final EditText titleEditText = new EditText(context);
        titleEditText.setLayoutParams(new GridLayout.LayoutParams(GridLayout.spec(0, 1, 1/2), GridLayout.spec(0, 2, 30)));
        titleEditText.setHint("Title");
        titleEditText.setId(R.id.titleEditText);
        titleEditText.setGravity(Gravity.TOP);
        addView(titleEditText);


        final Spinner spinnerType = new Spinner(context);
        spinnerType.setLayoutParams(new GridLayout.LayoutParams(GridLayout.spec(0, 1, 1/2), GridLayout.spec(2, 1, 1)));
        spinnerType.setId(R.id.spinnerType);
        ArrayAdapter<CharSequence> spinnerArrayAdapter = ArrayAdapter.createFromResource(
                context,
                R.array.noteTypesArray,
                android.R.layout.simple_spinner_item);
        spinnerType.setAdapter(spinnerArrayAdapter);
        spinnerType.setGravity(Gravity.TOP);
        addView(spinnerType);


        final EditText contentEditText = new EditText(context);
        contentEditText.setLayoutParams(layoutParams);
        contentEditText.setId(R.id.contentEditText);
        contentEditText.setGravity(Gravity.TOP);
        contentEditText.setHint("Content");
        addView(contentEditText);


        Button doneButton = new Button(context);
        GridLayout.LayoutParams buttonParams = new LayoutParams();
        buttonParams.rowSpec = GridLayout.spec(2, 1, 1/2);
        buttonParams.columnSpec = GridLayout.spec(0, 3, 1);
        doneButton.setLayoutParams(buttonParams);
        contentEditText.setLayoutParams(layoutParams);
        doneButton.setId(R.id.doneButton);
        doneButton.setGravity(Gravity.CENTER);
        doneButton.setText("Done");
        addView(doneButton);
    }
}