package com.example.maryam.jishoorg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maryam on 11/27/16.
 */

public class CustomAdapter extends ArrayAdapter <Word>{


    public CustomAdapter(Context context, ArrayList <Word> words) {
        super(context, R.layout.word_reading ,words);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.word_reading, parent, false);
        TextView word = (TextView) view.findViewById(R.id.word);
        TextView reading = (TextView) view.findViewById(R.id.reading);

        Word newWord = getItem(position);
        Pair pair = newWord.getJapanese(0);
        word.setText(pair.getWord());
        reading.setText(pair.getReading());

        return view;
    }
}
