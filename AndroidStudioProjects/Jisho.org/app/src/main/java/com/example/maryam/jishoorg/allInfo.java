package com.example.maryam.jishoorg;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class allInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_info);

        Word word = getIntent().getParcelableExtra("word");

        String carrot = "#EE693f";
        String tangerine = "#F69454";
        String offWhite = "#FCFDFE";
        String pearGreen = "#739F3D";
        String cerulean = "#006c84";
        String blueTopaz = "#6EB5C0";
        String avocado = "#0278a3";
        String tomato = "#C0B2B5";

        RelativeLayout layout = new RelativeLayout(this);
        layout.setBackgroundColor(Color.parseColor(offWhite));

        RelativeLayout.LayoutParams details;


        int size;
        int ID = 1;


        //meaning
        TextView meaning = new TextView(this);
        meaning.setText(word.getJapanese(0).getWord());
        meaning.setBackgroundColor(Color.parseColor(avocado));
        meaning.setTextColor(Color.parseColor(offWhite));
        meaning.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        meaning.setId(ID);
        meaning.setPadding(20, 20, 20, 5);
        details= new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        layout.addView(meaning, details);
        ++ID;


       //reading
        TextView reading = new TextView(this);
        reading.setText(word.getJapanese(0).getReading());
        reading.setBackgroundColor(Color.parseColor(avocado));
        reading.setTextColor(Color.parseColor(offWhite));
        reading.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        reading.setId(ID);
        reading.setPadding(20, 20, 20, 5);
        details= new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        details.addRule(RelativeLayout.BELOW, meaning.getId());
        details.setMargins(0, 0, 0, 50);
        layout.addView(reading, details);
        ++ID;



        //common
        TextView is_common = new TextView(this);
        if (word.getIs_common()) {
            is_common.setText("Common word");
            is_common.setBackgroundColor(Color.parseColor(tomato));
            is_common.setTextColor(Color.parseColor(offWhite));
            is_common.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            is_common.setId(ID);
            is_common.setPadding(15, 10, 10, 10);
            details= new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            details.setMargins(10, 0, 20, 40);
            details.addRule(RelativeLayout.BELOW, ID-1);
            layout.addView(is_common, details);
            ++ID;
        }

        //Tags
        size = word.getTagsNum();
        for (int i = 0; i < size; i++) {
            String tagHolder = word.getTag(i);
             TextView tag = new TextView(this);

            if (tagHolder.contains("wanikani"))
                tag.setText("Wanikani level " + tagHolder.substring(8));

            tag.setBackgroundColor(Color.parseColor(tomato));
            tag.setTextColor(Color.parseColor(offWhite));
            tag.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            tag.setId(ID);
            tag.setPadding(10, 10, 10, 10);
            details= new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            details.setMargins(10, 0, 20, 40);
            details.addRule(RelativeLayout.BELOW, reading.getId());
            details.addRule(RelativeLayout.RIGHT_OF, ID-1);
            layout.addView(tag, details);
            ++ID;
        }


        //senses
        size = word.getSensesNum();

        for (int i = 0; i < size; i++) {
            Senses sense = word.getSenses(i);
            int n;
            String stringHolder;

            //parts of speech
            n = sense.getParts_of_speechNum();
            stringHolder = "";
            for (int j = 0; j < n; j++) {
                stringHolder += sense.getPart_of_speech(j);
                if (j < n - 1) stringHolder += ", ";
            }
            if (stringHolder.length() > 0) {
                TextView parts_of_speech = new TextView(this);
                parts_of_speech.setText(stringHolder);
                parts_of_speech.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                parts_of_speech.setId(ID);
                parts_of_speech.setTextColor(Color.BLACK);
                details = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                details.setMargins(15, 0, 15, 0);
                details.addRule(RelativeLayout.BELOW, ID-1);
                layout.addView(parts_of_speech, details);
                ++ID;

            }



            //definitions
            n = sense.getEnglish_definitionsNum();
            stringHolder = "";
            for (int j = 0; j < n; j++) {
                stringHolder += sense.getEnglish_definition(j);
                if (j < n - 1) stringHolder += ", ";
            }
             TextView english_definitions = new TextView(this);
            english_definitions.setText(Integer.toString(i + 1) + "." + stringHolder);
            english_definitions.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            english_definitions.setId(ID);
            details = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            details.setMargins(15, 0 , 15, 30);
            details.addRule(RelativeLayout.BELOW, ID-1);
            layout.addView(english_definitions, details);
            ++ID;
        }

        //other forms
        size = word.getJapaneseNum();
        if (size - 1 > 0) {
            TextView otherForms = new TextView(this);
            otherForms.setText("Other forms");
            otherForms.setTextColor(Color.parseColor(offWhite));
            otherForms.setBackgroundColor(Color.parseColor(tomato));
            otherForms.setPadding(10, 10, 10, 10);
            otherForms.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            otherForms.setId(ID);
            details = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            details.setMargins(15, 40, 15, 10);
            details.addRule(RelativeLayout.BELOW, ID-1);
            layout.addView(otherForms, details);
            ++ID;
        }

        for (int i = 1; i < size; i++) {

            String meaning_reading = "";
            meaning_reading += word.getJapanese(i).getWord();
            if (meaning_reading.length() > 0 && meaning_reading.charAt(meaning_reading.length() - 1) != ' ')
                meaning_reading += "    ";
            meaning_reading += word.getJapanese(i).getReading();

            TextView otherForm = new TextView(this);
            otherForm.setText(meaning_reading);
            otherForm.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            otherForm.setId(ID);
            details = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            details.setMargins(15, 10, 15, 10);
            details.addRule(RelativeLayout.BELOW, ID-1);
            layout.addView(otherForm, details);
            ++ID;
        }


        setContentView(layout);

    }
}


