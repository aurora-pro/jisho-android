package com.example.maryam.jishoorg;

import java.io.StringReader;
import java.util.ArrayList;

public class Word{

    class Pair {
        private String word = null;
        private String reading = null;

        Pair (String word, String reading){
            this.word = word;
            this.reading = reading;
        }

        public void setWord (String word){
            this.word = word;
        }
        public void setReading (String reading){
            this.reading = reading;
        }
        public String getWord (){
            return word;
        }
        public String getReading (){
            return reading;
        }
    }

    private boolean is_common = false;
    private ArrayList <String> tags = new ArrayList<>();
    private ArrayList <Pair> japanese = new ArrayList<>();
    private ArrayList <Senses> senses = new ArrayList<>();
    private int tagsNum = 0;
    private int japaneseNum = 0;
    private int sensesNum = 0;

    public void setIs_common (boolean is_common){
        this.is_common = is_common;
    }
    public void setTag (String tag){
        tags.add(tag);
        tagsNum++;
    }
    public void setJapanese (String word, String reading){
        Pair p = new Pair(word, reading);
        japanese.add(p);
        japaneseNum++;
    }
    public void setSenses (Senses sense){
        senses.add(sense);
        sensesNum++;
    }
    public boolean getIs_common (){
        return is_common;
    }
    public String getTag (int idx){
        return tags.get(idx);
    }
    public Pair getJapanese (int idx){
        return japanese.get(idx);
    }
    public Senses getSenses (int idx){
        return senses.get(idx);
    }
    public int getTagsNum (){
        return tagsNum;
    }
    public int getJapaneseNum (){
        return japaneseNum;
    }
    public int getSensesNum (){
        return sensesNum;
    }

}


   /*
        data: array
            is_common: boolean
            tags: array
            japanese: array (word, reading)
            senses: array
                english_definitions: array
                parts_of_speech: array
                links: array
                tags: array
                restrictions: array
                see_also: array
                antonyms: array
                source: array
                info: array


     */
