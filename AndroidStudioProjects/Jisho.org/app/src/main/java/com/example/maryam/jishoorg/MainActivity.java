package com.example.maryam.jishoorg;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;


import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout layout = new RelativeLayout(this);
        String offWhite = "#FCFDFE";
       // layout.setBackgroundColor(Color.parseColor(offWhite));


    }

    public void clickSearch(View view) throws JSONException, ExecutionException, InterruptedException {

        EditText query = (EditText) findViewById(R.id.query);
        ListView data = (ListView) findViewById(R.id.Data);
        DownloadLink downloadLink = new DownloadLink();
        ArrayList<Word> words = new ArrayList<>();
        final ArrayList<Word> finalWords = new ArrayList<>();

        String url = "http://jisho.org/api/v1/search/words?keyword=";
        url += query.getText();
        downloadLink.execute(url);
        String result = downloadLink.get();

        ReadJson readJson = new ReadJson();
        words = readJson.read(result, 0);

        for (int i = 0; i < words.size(); i++)
            finalWords.add(words.get(i));

        ArrayAdapter arrayAdapter = new CustomAdapter(this, finalWords);
        data.setAdapter(arrayAdapter);

        final Context context = this;

        data.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(context, allInfo.class);
                intent.putExtra("word", finalWords.get(i));
                startActivity(intent);
            }
        });

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

