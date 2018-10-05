package com.example.feelsbook;

/*

    Purpose: represent emotion

    Rationale: originally designed as an abstract class that
    will be extended to different emotions.  However, later this
    sole class is used to represent all emotions because it is more
    convenient for Gson to parse an Arraylist of Emotion rather than
    an ArrayList of subclasses of Emotion.

 */

public class Emotion {

    private String emotion;

    public Emotion(String emotion){
        this.emotion = emotion;
    }

    public String toString(){
        return this.emotion;
    }
}
