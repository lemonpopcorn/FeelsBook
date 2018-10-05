package com.example.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/*
    Purpose: Activity class used to let user modify a past emotion

    Design Rationale: Rather than putting everything under MainActivity,
    the edit page is extracted out to preserve the separation of concern.

    Outstanding issues: So far, only the deletion of a past emotion is
    implemented.  That is, although you can delete a past emotion, you
    cannot edit it once it's been created.  However, this activity was
    planned to include the edit functionality as well initially, this
    results in a quite ugly layout with a single delete button.
*/
public class EditRecordActivity extends AppCompatActivity {

    ArrayList<Record> records = Singleton.getInstance().getRecords();
    EmotionStats stats = Singleton.getInstance().getStats();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        Intent intent = getIntent();
        final Integer position = (Integer) intent.getSerializableExtra(DisplayHistoryActivity.EXTRA_MESSAGE);

        // Button concerned with the deletion of emotions
        Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Record record = records.remove((int)position);
                // Decrement the count for the deleted emotion
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
                        stats.decrementSurprise();
                        break;
                    case MainActivity.SADNESS_TEXT:
                        stats.decrementSadness();
                        break;
                }
                // Return back to the history activity
                finish();
            }
        });
    }
}
