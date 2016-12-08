package com.example.maryam.jishoorg;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.StringReader;
import java.util.ArrayList;

import static com.example.maryam.jishoorg.Pair.*;

public class Word implements Parcelable{


    private boolean is_common = false;
    private ArrayList <String> tags = new ArrayList<>();
    private ArrayList <Pair> japanese = new ArrayList<>();
    private ArrayList <Senses> senses = new ArrayList<>();
    private int tagsNum = 0;
    private int japaneseNum = 0;
    private int sensesNum = 0;

    Word (){
      is_common = false;
        tags = new ArrayList<>();
       japanese = new ArrayList<>();
        senses = new ArrayList<>();
       tagsNum = 0;
      japaneseNum = 0;
         sensesNum = 0;
    }

    protected Word(Parcel in) {
        is_common = in.readByte() != 0;
        tags = in.createStringArrayList();
        japanese = in.createTypedArrayList(Pair.CREATOR);
        senses = in.createTypedArrayList(Senses.CREATOR);
        tagsNum = in.readInt();
        japaneseNum = in.readInt();
        sensesNum = in.readInt();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (is_common ? 1 : 0));
        parcel.writeStringList(tags);
        parcel.writeTypedList(japanese);
        parcel.writeTypedList(senses);
        parcel.writeInt(tagsNum);
        parcel.writeInt(japaneseNum);
        parcel.writeInt(sensesNum);
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
