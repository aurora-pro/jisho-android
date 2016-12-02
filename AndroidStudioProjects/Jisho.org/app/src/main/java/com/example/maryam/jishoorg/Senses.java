package com.example.maryam.jishoorg;

import java.util.ArrayList;

public class Senses {

    private ArrayList<String> english_definitions = new ArrayList<>();
    private ArrayList <String> parts_of_speech = new ArrayList<>();
    private ArrayList <String> links = new ArrayList<>();
    private ArrayList <String> tags = new ArrayList<>();
    private ArrayList <String> restrictions = new ArrayList<>();
    private ArrayList <String> see_also = new ArrayList<>();
    private ArrayList <String> antonyms = new ArrayList<>();
    private ArrayList <String> info = new ArrayList<>();
    private ArrayList <String> sources = new ArrayList<>();
    private int english_definitionsNum = 0;
    private int parts_of_speechNum = 0;
    private int linksNum= 0;
    private int tagsNum= 0;
    private int restrictionsNum= 0;
    private int see_alsoNum= 0;
    private int antonymsNum= 0;
    private int infoNum= 0;
    private int sourcesNum= 0;


    public void setEnglish_definition (String english_definition){
        english_definitions.add(english_definition);
        english_definitionsNum++;
    }
    public void setPart_of_speech (String part_of_speech){
        parts_of_speech.add(part_of_speech);
        parts_of_speechNum++;
    }
    public void setLink (String link){
        links.add(link);
        linksNum++;
    }
    public void setTag (String tag){
        tags.add(tag);
        tagsNum++;
    }
    public void setRestriction (String restriction){
        restrictions.add(restriction);
        restrictionsNum++;
    }
    public void setSee_also (String see_also){
        this.see_also.add(see_also);
        see_alsoNum++;
    }
    public void setAntonym (String antonym){
        antonyms.add(antonym);
        antonymsNum++;
    }
    public void setSources (String source){
        sources.add(source);
        sourcesNum++;
    }
    public void setInfo (String info){
        this.info.add(info);
        infoNum++;
    }
    public String getEnglish_definition(int idx){
        return english_definitions.get(idx);
    }
    public String getPart_of_speech (int idx){
        return parts_of_speech.get(idx);
    }
    public String getLink (int idx){
        return links.get(idx);
    }
    public String getTag (int idx){
        return tags.get(idx);
    }
    public String getRestriction (int idx){
        return restrictions.get(idx);
    }
    public String getSee_also (int idx){
        return see_also.get(idx);
    }
    public String getAntonym (int idx){
        return antonyms.get(idx);
    }
    public String getSource (int idx){
        return sources.get(idx);
    }
    public String getInfo (int idx){
        return info.get(idx);
    }
    public int getEnglish_definitionsNum (){
        return english_definitionsNum;
    }
    public int getParts_of_speechNum (){
        return parts_of_speechNum;
    }
    public int getLinksNum (){
        return linksNum;
    }
    public int getTagsNum (){
        return tagsNum;
    }
    public int getRestrictionsNum (){
        return restrictionsNum;
    }
    public int getSee_alsoNum (){
        return see_alsoNum;
    }
    public int getAntonymsNum(){
        return antonymsNum;
    }
    public int getInfoNum (){
        return infoNum;
    }
    public int getSourcesNum (){
        return sourcesNum;
    }
}
