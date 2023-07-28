package com.example.recl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
public class TimeTable extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);
        Intent intent = getIntent();
        String text = intent.getStringExtra("classnum");
        TextView numofclass = (TextView) findViewById(R.id.numofclass);
        numofclass.setText(text);

    }
}


