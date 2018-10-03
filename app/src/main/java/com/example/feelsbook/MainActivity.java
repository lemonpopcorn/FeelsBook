package com.example.feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<Record> records = Singleton.getInstance().getRecords();
    EmotionStats stats = Singleton.getInstance().getStats();

    static final String RECORDS_KEY = "records.json";
    static final String STATS_KEY = "stats.json";

    static final String LOVE_TEXT = "LOVE";
    static final String JOY_TEXT = "JOY";
    static final String SURPRISE_TEXT = "SURPRISE";
    static final String SADNESS_TEXT = "SADNESS";
    static final String ANGER_TEXT = "ANGER";
    static final String FEAR_TEXT = "FEAR";

    public void addRecord(Emotion emotion, EditText comment_box) {
        Date timestamp = new Date();
        String comment = comment_box.getText().toString();
        comment_box.setText("");
        records.add(new Record(emotion, timestamp, comment));
        Log.d("lemon", records.get(records.size() - 1).toString());
    }

    // Called when the user press the HISTORY button
    public void displayHistory(View view){
        Intent intent = new Intent(this, DisplayHistoryActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("lemon file loading at", this.getApplicationInfo().dataDir);
        File directory = new File(this.getApplicationInfo().dataDir);
        File[] files = directory.listFiles();
        for (File file : files){
            Log.d("Lemon Files:", file.getName());
        }

        final EditText comment_box = findViewById(R.id.comment_box);

        final Button LoveButton = findViewById(R.id.love_button);
        LoveButton.setText("Love: " + stats.getLoveCount().toString());
        LoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stats.incrementLove();
                LoveButton.setText("Love: " + stats.getLoveCount().toString());
                addRecord(new Emotion(LOVE_TEXT), comment_box);
            }
        });

        final Button JoyButton = findViewById(R.id.joy_button);
        JoyButton.setText("Joy: " + stats.getJoyCount().toString());
        JoyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stats.incrementJoy();
                JoyButton.setText("Joy: " + stats.getJoyCount().toString());
                addRecord(new Emotion(JOY_TEXT), comment_box);
            }
        });

        final Button SurpriseButton = findViewById(R.id.surprise_button);
        SurpriseButton.setText("Surprise: " + stats.getSurpriseCount().toString());
        SurpriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stats.incrementSurprise();
                SurpriseButton.setText("Surprise: " + stats.getSurpriseCount().toString());
                addRecord(new Emotion(SURPRISE_TEXT), comment_box);
            }
        });

        final Button AngerButton = findViewById(R.id.anger_button);
        AngerButton.setText("Anger: " + stats.getAngerCount().toString());
        AngerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stats.incrementAnger();
                AngerButton.setText("Anger: " + stats.getAngerCount().toString());
                addRecord(new Emotion(ANGER_TEXT), comment_box);
            }
        });

        final Button SadnessButton = findViewById(R.id.sadness_button);
        SadnessButton.setText("Sadness: " + stats.getSadnessCount().toString());
        SadnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stats.incrementSadness();
                SadnessButton.setText("Sadness: " + stats.getSadnessCount().toString());
                addRecord(new Emotion(SADNESS_TEXT), comment_box);
            }
        });

        final Button FearButton = findViewById(R.id.fear_button);
        FearButton.setText("Fear: " + stats.getFearCount().toString());
        FearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stats.incrementFear();
                FearButton.setText("Fear: " + stats.getFearCount().toString());
                addRecord(new Emotion(FEAR_TEXT), comment_box);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        // Update the buttons text to display the new stats
        Button LoveButton = findViewById(R.id.love_button);
        LoveButton.setText("Love: " + stats.getLoveCount().toString());

        Button FearButton = findViewById(R.id.fear_button);
        FearButton.setText("Fear: " + stats.getFearCount().toString());

        Button SurpriseButton = findViewById(R.id.surprise_button);
        SurpriseButton.setText("Surprise: " + stats.getSurpriseCount().toString());

        Button JoyButton = findViewById(R.id.joy_button);
        JoyButton.setText("Joy: " + stats.getJoyCount().toString());

        Button AngerButton = findViewById(R.id.anger_button);
        AngerButton.setText("Anger: " + stats.getAngerCount().toString());

        Button SadnessButton = findViewById(R.id.sadness_button);
        SadnessButton.setText("Sadness: " + stats.getSadnessCount().toString());

    }

    @Override
    protected void onStop(){

        try {

            // CMPUT 301 Lab code (Shaiful Chowdhury)

            Gson gson = new Gson();

            // Save the records
            FileOutputStream fos = openFileOutput(RECORDS_KEY, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            gson.toJson(records, out);
            out.flush();

            // Save the stats
            fos = openFileOutput(STATS_KEY, Context.MODE_PRIVATE);
            out = new BufferedWriter(new OutputStreamWriter(fos));
            gson.toJson(stats, out);
            out.flush();

        } catch (Exception e) {
            Log.d("lemon", "This should not happen!!!!");
            e.printStackTrace();
        }

        super.onStop();
        Log.d("lemon", "DESTOROYARU");
        Log.d("lemon file saved at", this.getApplicationInfo().dataDir);
    }

}
