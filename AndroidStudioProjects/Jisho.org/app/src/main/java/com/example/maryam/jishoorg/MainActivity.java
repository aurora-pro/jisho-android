package com.example.maryam.jishoorg;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickSearch (View view) throws JSONException, ExecutionException, InterruptedException {

        EditText query = (EditText) findViewById(R.id.query);
        ListView data = (ListView) findViewById(R.id.Data);
        DownloadLink downloadLink = new DownloadLink();
        ArrayList <Word> words = new ArrayList<>();
        final ArrayList <Word> finalWords = new ArrayList<>();

        String url = "http://jisho.org/api/v1/search/words?keyword=";
        url+=query.getText();
        downloadLink.execute(url);
        String result = downloadLink.get();

        ReadJson readJson = new ReadJson();
        words = readJson.read(result, 0);

        for (int i = 0; i < words.size(); i++)
            finalWords.add(words.get(i));

        ArrayAdapter arrayAdapter = new CustomAdapter(this, finalWords);
        data.setAdapter(arrayAdapter);


        data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewInfo(finalWords.get(i));
            }
        });

    }

    public void viewInfo (Word word){

        setContentView(R.layout.all_info);
        View linearLayout = findViewById(R.id.all_info);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams llp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(20, 20, 20, 5);
        String carrot = "#EE693f";
        String tangerine = "#F69454";
        String offWhite = "#FCFDFE";
        String pearGreen = "#739F3D";
        String cerulean = "#006c84";
        String blueTopaz = "#6EB5C0";
        String avocado = "#0278a3";
        String tomato = "#C0B2B5";
        int size;

        //meaning
        TextView meaning = new TextView(this);
        meaning.setText(word.getJapanese(0).getWord());
        meaning.setTextAppearance(this, R.style.meaning_reading);
        meaning.setBackgroundColor(Color.parseColor(avocado));
        meaning.setPadding(20, 20, 20, 5);
        meaning.setLayoutParams(llp2);
        ((LinearLayout) linearLayout).addView(meaning);

        //reading
        TextView reading = new TextView(this);
        reading.setText(word.getJapanese(0).getReading());
        reading.setTextAppearance(this, R.style.meaning_reading);
        reading.setBackgroundColor(Color.parseColor(avocado));
        reading.setPadding(20, 20, 20, 5);
        reading.setLayoutParams(llp2);
        ((LinearLayout) linearLayout).addView(reading);


        //common
        TextView is_common = new TextView(this);
        if (word.getIs_common()) {
            is_common.setText("Common word");
            is_common.setTextAppearance(this,R.style.is_common);
            is_common.setBackgroundColor(Color.parseColor(tomato));
            is_common.setTextColor(Color.parseColor(offWhite));
            is_common.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            is_common.setPadding(20, 20, 20, 20);
            llp.setMargins(20, 40, 20, 20);
            is_common.setLayoutParams(llp);
            ((LinearLayout) linearLayout).addView(is_common);
        }

        //Tags
        size = word.getTagsNum();
        for (int i = 0; i < size; i++){
            String tagHolder = word.getTag(i);
            TextView tag = new TextView(this);

            if (tagHolder.contains("wanikani"))
                tag.setText("Wanikani level " + tagHolder.substring(8));

            tag.setBackgroundColor(Color.parseColor(tomato));
            tag.setTextColor(Color.parseColor(offWhite));
            tag.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            llp.setMargins(20, 40, 20, 10);
            tag.setPadding(20, 20, 20, 20);
            tag.setLayoutParams(llp);
            ((LinearLayout) linearLayout).addView(tag);

        }

        //senses
        llp.setMargins(20, 20, 20, 5);
        size = word.getSensesNum();
        for (int i = 0; i < size; i++){
            Senses sense = word.getSenses(i);
            int n;
            String stringHolder;

            //parts of speech
            n = sense.getParts_of_speechNum();
            stringHolder = "";
            for (int j = 0; j < n; j++){
                stringHolder+=sense.getPart_of_speech(j);
                if (j < n - 1) stringHolder+=", ";
            }
            if (stringHolder.length() > 0) {
                TextView parts_of_speech = new TextView(this);
                parts_of_speech.setText(stringHolder);
                parts_of_speech.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                parts_of_speech.setLayoutParams(llp);
                ((LinearLayout) linearLayout).addView(parts_of_speech);
            }

            //definitions
            n = sense.getEnglish_definitionsNum();
            stringHolder = "";
            for (int j = 0; j < n; j++){
                stringHolder+=sense.getEnglish_definition(j);
                if (j < n-1) stringHolder+=", ";
            }
            Log.i("definitions ", stringHolder);
            TextView english_definitions = new TextView(this);
            english_definitions.setText(Integer.toString(i+1) + "." + stringHolder);
            llp.setMargins(20, 20, 20, 10);
            english_definitions.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            english_definitions.setLayoutParams(llp);
            ((LinearLayout) linearLayout).addView(english_definitions);

        }

        //other forms
        size = word.getJapaneseNum();

        if (size - 1 > 0) {
            TextView otherForms = new TextView(this);
            otherForms.setText("Other forms");
            otherForms.setTextColor(Color.parseColor(offWhite));
            otherForms.setBackgroundColor(Color.parseColor(tomato));
            otherForms.setPadding(20, 20, 20, 20);
            otherForms.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            llp.setMargins(0, 0, 0, 0);
            otherForms.setLayoutParams(llp);
            ((LinearLayout) linearLayout).addView(otherForms);
        }

        llp.setMargins(20, 20, 20, 5);

        for (int i = 1; i < size; i++){

            String meaning_reading = "";
            meaning_reading+=word.getJapanese(i).getWord();
            if (meaning_reading.charAt(meaning_reading.length()-1) != ' ')
                meaning_reading+=" ";
            meaning_reading+=word.getJapanese(i).getReading();

            TextView otherForm = new TextView(this);
            otherForm.setText(meaning_reading);
            otherForm.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            otherForm.setLayoutParams(llp);
            ((LinearLayout) linearLayout).addView(otherForm);
        }


    }
}


    /*
    todo:
        add meaning to list at the top
        add colors
        add %20 to spaces
        load more
        textwatcher

 */

