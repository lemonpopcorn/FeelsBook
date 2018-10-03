package com.example.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class EditRecordActivity extends AppCompatActivity {

    ArrayList<Record> records = Singleton.getInstance().getRecords();
    EmotionStats stats = Singleton.getInstance().getStats();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        Intent intent = getIntent();
        final Integer position = (Integer) intent.getSerializableExtra(DisplayHistoryActivity.EXTRA_MESSAGE);
        Log.d("lemon EditRecord", position.toString());

        Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Record record = records.remove((int)position);
                Log.d("lemon yeaaaa", record.getEmotion().toString());
                // decrement the count depending on which emotion is removed
                switch (record.getEmotion().toString()){
                    case MainActivity.LOVE_TEXT:
                        stats.decrementLove();
                        break;
                    case MainActivity.ANGER_TEXT:
                        stats.decrementAnger();
                        break;
                    case MainActivity.FEAR_TEXT:
                        stats.decrementFear();
                        break;
                    case MainActivity.JOY_TEXT:
                        stats.decrementJoy();
                        break;
                    case MainActivity.SURPRISE_TEXT:
                        Log.d("lemon", "what?");
                        stats.decrementSurprise();
                        break;
                    case MainActivity.SADNESS_TEXT:
                        stats.decrementSadness();
                        break;
                }
                finish();
            }
        });
    }
}
