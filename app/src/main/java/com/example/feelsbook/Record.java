package com.example.feelsbook;

import com.example.feelsbook.Emotion;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/*

    Purpose: Represent a single emotion entry

    Rationale: A single entry should be an entity that
    combines the emotion, the text description, and a
    date that the user enters.

 */

public class Record implements Serializable {

    private Emotion emotion;
    private Date timestamp;
    private String comment = "";

    public Record(Emotion emotion, Date timestamp){
        this.emotion = emotion;
        this.timestamp = timestamp;
    }

    public Record(Emotion emotion, Date timestamp, String comment){
        this.emotion = emotion;
        this.timestamp = timestamp;
        this.comment = comment;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public String toString(){

        // Joachim Sauer (40342), L S (543738): https://stackoverflow.com/questions/3914404/how-to-get-current-moment-in-iso-8601-format-with-date-hour-and-minute
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String nowAsISO = df.format(timestamp);
        return nowAsISO + " | " + this.emotion.toString() + " | " + this.comment;
    }
}
