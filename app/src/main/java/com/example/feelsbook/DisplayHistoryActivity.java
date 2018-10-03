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

        recordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("lemon", new Integer(position).toString() + " " + new Long(id).toString());
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
        super.onResume();
        recordAdapter.notifyDataSetChanged();
        Log.d("lemon Delete2", new Integer(records.size()).toString());
    }
}
