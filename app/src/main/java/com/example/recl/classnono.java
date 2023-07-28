package com.example.recl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class classnono extends AppCompatActivity implements View.OnClickListener {
    static boolean calledAlready = false;
    TextView dateNow;
    Button refreshbutton;
    int hour, mhour, minute, minute_sum, day;
    String day_string, gyosi_1, gyosi_2;
    LocalDateTime now = LocalDateTime.now();
    DatabaseReference mDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classnono);
        dateNow = (TextView) findViewById(R.id.dateNow);
        dateNow.setText(getTime());
        refreshbutton = findViewById(R.id.refreshbutton);
        refreshbutton.setOnClickListener(this);

        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        hour = now.getHour();
        mhour = hour*60;
        minute = now.getMinute();
        minute_sum = mhour+minute;
        day = now.getDayOfWeek().getValue();

        if (day ==1)
            day_string = "MON";
        else if (day ==2)
            day_string = "TUE";
        else if (day ==3)
            day_string = "WED";
        else if (day ==4)
            day_string = "THU";
        else if (day ==5)
            day_string = "FRI";

        if(day == 1 || day==3 || day==5) {
            if (hour == 9)
                gyosi_1 = "1st";
            else if (hour == 10)
                gyosi_1 = "2nd";
            else if (hour == 11)
                gyosi_1 = "3rd";
            else if (hour == 12)
                gyosi_1 = "4th";
            else if (hour == 13)
                gyosi_1 = "5th";
            else if (hour == 14)
                gyosi_1 = "6th";
            else if (hour == 15)
                gyosi_1 = "7th";
            else if (hour == 16)
                gyosi_1 = "8th";
            else if (hour == 17)
                gyosi_1 = "9th";
        }
        else if(day == 2 || day== 4){
            if(minute_sum >= 540 && minute_sum<630)
                gyosi_2 = "1st";
            else if(minute_sum >= 630 && minute_sum<720)
                gyosi_2 = "2nd";
            else if(minute_sum >= 720 && minute_sum<810)
                gyosi_2 = "3rd";
            else if(minute_sum >= 810 && minute_sum<900)
                gyosi_2 = "4th";
            else if(minute_sum >= 900 && minute_sum<990)
                gyosi_2 = "5th";
            else if(minute_sum >= 990 && minute_sum<1080)
                gyosi_2 = "6th";
        }

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }









    public String getTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.refreshbutton){
            dateNow.setText(getTime());
        }
    }



}
