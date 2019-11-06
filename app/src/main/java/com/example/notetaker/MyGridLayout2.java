package com.example.notetaker;

import android.content.Context;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

public class MyGridLayout2 extends GridLayout {

    public MyGridLayout2(final Context context){
        super(context);
//        setBackground(context.getDrawable(GridLayout));
        setColumnCount(2);


        EditText titleEditText = new EditText(context);
        titleEditText.setHint("Title");
        GridLayout.Spec editTextRowSpec = GridLayout.spec(0, 1, 1/2);
        GridLayout.Spec editTextColSpec = GridLayout.spec(0,1,20);
        GridLayout.LayoutParams layoutParams1 = new GridLayout.LayoutParams(editTextRowSpec, editTextColSpec);
        titleEditText.setLayoutParams(layoutParams1);
        titleEditText.setId(R.id.titleEditText);

        Spinner spinner = new Spinner(context);
        spinner.setId(R.id.spinner);
        GridLayout.Spec spinnerRowSpec = GridLayout.spec(0,1,1/2);
        GridLayout.Spec spinnerColSpec = GridLayout.spec(1,1,1);
        GridLayout.LayoutParams layoutParams2 = new GridLayout.LayoutParams(spinnerRowSpec, spinnerColSpec);
        spinner.setLayoutParams(layoutParams2);

        EditText enterText = new EditText(context);
        enterText.setHint("Content");
        GridLayout.Spec enterTextRowSpec = GridLayout.spec(1, 1, 15);
        GridLayout.Spec enterTextColSpec = GridLayout.spec(0,2,1);
        GridLayout.LayoutParams layoutParams3 = new GridLayout.LayoutParams(enterTextRowSpec, enterTextColSpec);
        enterText.setGravity(Gravity.TOP);
        enterText.setId(R.id.content);
        enterText.setLayoutParams(layoutParams3);

        Button myButton = new Button(context);
        myButton.setId(R.id.doneButton);
        myButton.setText("Done");
        GridLayout.Spec buttonRowSpec = GridLayout.spec(2, 1, 1);
        GridLayout.Spec buttonColSpec = GridLayout.spec(0,2,1);
        GridLayout.LayoutParams layoutParams4 = new GridLayout.LayoutParams(buttonRowSpec, buttonColSpec);
        myButton.setLayoutParams(layoutParams4);

        addView(titleEditText);
        addView(spinner);
        addView(enterText);
        addView(myButton);
    }
}
