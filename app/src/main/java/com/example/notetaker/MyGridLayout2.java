package com.example.notetaker;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
        List<String> typesList = new ArrayList<String>();

        //creates the types for the spinner
        typesList.add("Personal");
        typesList.add("School");
        typesList.add("Work");
        typesList.add("Other");

        //override methods for spinnerArrayAdapter to show and display the respective spinner pictures
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context,
                android.R.layout.activity_list_item, android.R.id.text1, typesList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                switch (position){
                    case 0:
                        ImageView personalImage = (ImageView) view.findViewById(android.R.id.icon);
                        personalImage.setImageResource(R.drawable.person);
                        break;
                    case 1:
                        ImageView schoolImage = (ImageView) view.findViewById(android.R.id.icon);
                        schoolImage.setImageResource(R.drawable.school);
                        break;
                    case 2:
                        ImageView workImage = (ImageView) view.findViewById(android.R.id.icon);
                        workImage.setImageResource(R.drawable.work);
                        break;
                    case 3:
                        ImageView otherImage = (ImageView) view.findViewById(android.R.id.icon);
                        otherImage.setImageResource(R.drawable.other);
                        break;
                }
                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                switch (position){
                    case 0:
                        ImageView personalImage = (ImageView) view.findViewById(android.R.id.icon);
                        personalImage.setImageResource(R.drawable.person);
                        break;
                    case 1:
                        ImageView schoolImage = (ImageView) view.findViewById(android.R.id.icon);
                        schoolImage.setImageResource(R.drawable.school);
                        break;
                    case 2:
                        ImageView workImage = (ImageView) view.findViewById(android.R.id.icon);
                        workImage.setImageResource(R.drawable.work);
                        break;
                    case 3:
                        ImageView otherImage = (ImageView) view.findViewById(android.R.id.icon);
                        otherImage.setImageResource(R.drawable.other);
                        break;
                }
                return view;
            }
        };

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
