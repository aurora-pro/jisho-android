package com.example.maryam.jishoorg;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReadJson {

    int resultShowedNumber = 10;

    public ArrayList<Word> read (String result, int startIdx) throws JSONException {

        ArrayList<Word> words = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(result);
        JSONArray data = (JSONArray) jsonObject.get("data");
        int dataSize = data.length();
        int endIdx = startIdx + resultShowedNumber;
        if (dataSize - startIdx < endIdx) endIdx = dataSize - startIdx;

        for (int i = startIdx; i < endIdx + startIdx; i++) {

            Word newWord = new Word();
            JSONObject word = data.getJSONObject(i);

            //is_common
            newWord.setIs_common(word.getBoolean("is_common"));

            //tags
            JSONArray tags = (JSONArray) word.get("tags");
            for (int j = 0; j < tags.length(); j++) {
                String tag = tags.get(j).toString();
                newWord.setTag(tag);
            }

            //japanese
            JSONArray japanese = (JSONArray) word.get("japanese");
            for (int j = 0; j < japanese.length(); j++) {
                JSONObject tmp = japanese.getJSONObject(j);

                String w = "", r = "";
                if (tmp.has("word")) w = tmp.getString("word");
                if (tmp.has("reading")) r = tmp.getString("reading");
                newWord.setJapanese(w, r);

            }

            //senses
            JSONArray senses = (JSONArray) word.get("senses");
            for (int j = 0; j < senses.length(); j++){
                Senses sense = new Senses();
                JSONObject s = senses.getJSONObject(j);

                //english_definitions
                JSONArray english_definitions = (JSONArray) s.get("english_definitions");
                for (int k = 0; k < english_definitions.length(); k++){
                    String english_definition = english_definitions.getString(k);
                    sense.setEnglish_definition(english_definition);
                }

                //parts of speech
                JSONArray parts_of_speech = (JSONArray) s.get ("parts_of_speech");
                for (int k = 0; k < parts_of_speech.length(); k++){
                    String part_of_speech = parts_of_speech.getString(k);
                    sense.setPart_of_speech(part_of_speech);
                }

                //links
                JSONArray links = (JSONArray) s.get("links");
                for (int k = 0; k < links.length(); k++){
                    String link = links.getString(k);
                    sense.setLink(link);
                }

                //tags
                tags = (JSONArray) s.get("tags");
                for (int k = 0; k < tags.length(); k++){
                    String tag = tags.getString(k);
                    sense.setTag(tag);
                }

                //restrictions
                JSONArray restrictions = (JSONArray)s.get("restrictions");
                for (int k = 0; k < restrictions.length(); k++){
                    String restriction = restrictions.getString(k);
                    sense.setRestriction(restriction);
                }

                //see_also
                JSONArray see_also = (JSONArray) s.get("see_also");
                for (int k = 0; k < see_also.length(); k++){
                    String see = see_also.getString(k);
                    sense.setSee_also(see);
                }

                //antonyms
                JSONArray antonyms = (JSONArray) s.get("antonyms");
                for (int k = 0; k < antonyms.length(); k++){
                    String antonym = antonyms.getString(k);
                    sense.setAntonym(antonym);
                }

                //source
                JSONArray sources = (JSONArray) s.get("source");
                for (int k = 0; k < sources.length(); k++){
                    String source = sources.getString(k);
                    sense.setSources(source);
                }

                //info
                JSONArray info = (JSONArray) s.get("info");
                for (int k = 0; k < info.length(); k++){
                    String info1 = info.getString(k);
                    sense.setInfo(info1);
                }
                newWord.setSenses(sense);

            }
            words.add(newWord);
            Log.i ("new word ", "added!");
        }
        return words;
    }

}

//links":[{"text":"Read “Zombie (computer science)” on English Wikipedia","url":"http://en.wikipedia.org/wiki/Zombie_(computer_science)?oldid=491539395"}