package com.example.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/*
    Purpose: An activity used to display the history of past activities

    Design Rationale: The main activity is crowded in the sense that
    there are already quite a few number of emotion buttons.  In addition,
    the main activity already shows the number of each emotion, so I believfe
    it's better to separate the history into another activity

 */

public class DisplayHistoryActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.feelsbook.POSITION";
    private ArrayList<Record> records = Singleton.getInstance().getRecords();
    private ArrayAdapter<Record> recordAdapter;
    private ListView recordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recordAdapter = new ArrayAdapter<>(this, R.layout.activity_display_history, R.id.record, records);
        recordList = new ListView(this);
        recordList.setAdapter(recordAdapter);
        setContentView(recordList);

        // Listens to click on the history items and launch the EditActivity
        // to edit the clicked item
        recordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DisplayHistoryActivity.this, EditRecordActivity.class);
                intent.putExtra(EXTRA_MESSAGE, new Integer(position));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        // Update the history on resume
        super.onResume();
        recordAdapter.notifyDataSetChanged();
    }
}
