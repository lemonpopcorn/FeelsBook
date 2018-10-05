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

/*

    Purpose: allows data sharing between without constantly saving
    to disk

    Rationale: putExtra using intents allow copies of records and stats
    to be passed around but not their reference.  This means that one
    class has to be responsible of updating those variables.  A singleton
    class allows these variables to be modified and read throughout each
    activity.


 */

public class Singleton {

    // initialize the only instance of singleton
    private static final Singleton instance = new Singleton();

    // objects to be shared
    private ArrayList<Record> records = new ArrayList<>();
    private EmotionStats stats = new EmotionStats();

    // load history from files
    public void loadData(){
        try {

            // CMPUT 301 Lab code (Shaiful Chowdhury)

            Gson gson = new Gson();

            // Initialize records
            FileInputStream fis = new FileInputStream("/data/user/0/com.example.feelsbook/files/records.json");
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            records = gson.fromJson(in, new TypeToken<ArrayList<Record>>(){}.getType());

            // Initialize stats
            fis = new FileInputStream("/data/user/0/com.example.feelsbook/files/stats.json");
            in = new BufferedReader(new InputStreamReader(fis));
            stats = gson.fromJson(in, new TypeToken<EmotionStats>(){}.getType());

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    // At construction, read from the files
    private Singleton(){
        loadData();
    }

    // public getters

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
