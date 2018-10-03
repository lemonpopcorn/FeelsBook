package com.example.feelsbook;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Singleton {

    private static final Singleton instance = new Singleton();
    private ArrayList<Record> records = new ArrayList<>();
    private EmotionStats stats = new EmotionStats();

    public void loadData(){
        try {

            // CMPUT 301 Lab code (Shaiful Chowdhury)

            Gson gson = new Gson();

            // Initialize records
            Log.d("lemon", "doing good!");
            FileInputStream fis = new FileInputStream("/data/user/0/com.example.feelsbook/files/records.json");
            Log.d("lemon", "eveb better!");
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            records = gson.fromJson(in, new TypeToken<ArrayList<Record>>(){}.getType());

            // Initialize stats
            fis = new FileInputStream("/data/user/0/com.example.feelsbook/files/stats.json");
            in = new BufferedReader(new InputStreamReader(fis));
            stats = gson.fromJson(in, new TypeToken<EmotionStats>(){}.getType());

        } catch (FileNotFoundException e){
            Log.d("Lemon Yes!?", "No previous data found.");
            e.printStackTrace();
        }
    }

    private Singleton(){
//        File file = new File("/data/user/0/com.example.feelsbook/files/records.json");
        File file = new File("files/records.json");
        Log.d("Lemon file exists", new Boolean(file.exists()).toString());

        loadData();
    }

    public static Singleton getInstance() {
        return instance;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public EmotionStats getStats(){
        return this.stats;
    }
}
