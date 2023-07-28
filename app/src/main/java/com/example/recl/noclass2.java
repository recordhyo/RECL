package com.example.recl;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



@RequiresApi(api = Build.VERSION_CODES.O)
public class noclass2 extends AppCompatActivity implements View.OnClickListener{

    Button refreshbutton;
    ArrayAdapter<String> adapter;
    ArrayList<String> noclasslist = new ArrayList<>();
    ListView listView;
    TextView dateNow = (TextView) findViewById(R.id.dateNow);
    int hour;
    int minute;
    int day;
    int mhour;
    int minute_sum;
    String day_string;
    String gyosi_1;
    String gyosi_2;
    DatabaseReference mDatabase;
    LocalDateTime now = LocalDateTime.now();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noclass);
        dateNow.setText(getTime());
        refreshbutton = (Button) findViewById(R.id.refreshbutton);
        adapter = new ArrayAdapter<String>(this,R.layout.list_item,noclasslist);
        listView = (ListView)findViewById(R.id.noclass_list);
        listView.setAdapter(adapter);

        hour = now.getHour();
        mhour = hour*60;
        minute = now.getMinute();
        minute_sum = mhour+minute;
        refreshbutton.setOnClickListener(this);
        day = now.getDayOfWeek().getValue();

/*        if (day ==1)
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
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG, ","loadPost:onCancelled", databaseError.toException());
            }
        };*/
    }

    public String getTime(){
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd  HH:mm분"));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.refreshbutton) {
            dateNow.setText(getTime());
        }
    }
}
