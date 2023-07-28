package com.example.recl;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;


public class Noclass extends AppCompatActivity implements View.OnClickListener{

    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy.MM.dd a HH:mm");
    TextView dateNow;
    TextView yoil;
    ListView listView;
    Button refreshbutton;
    String day_string = "";
    String gyosi_1 = "";
    String gyosi_2 = "";
    public ArrayList<String> noclasslist = new ArrayList<String>();
    public ArrayAdapter adapter;
    static boolean calledAlready = false;

    private int hour;
    private int minute;
    private int day;
    private int minute_sum;
    private int mhour;


    Calendar today = Calendar.getInstance();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noclass);

        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        dateNow = (TextView) findViewById(R.id.dateNow);
        dateNow.setText(getTime());
        refreshbutton = (Button) findViewById(R.id.refreshbutton);
        adapter = new ArrayAdapter<String>(this,R.layout.list_item,noclasslist);
        listView = (ListView)findViewById(R.id.noclass_list);
        listView.setAdapter(adapter);

        hour = today.get(today.HOUR_OF_DAY);
        mhour = hour*60;
        minute = today.get(today.MINUTE);
        minute_sum = mhour+minute;

        refreshbutton.setOnClickListener(this);

        day = today.get(Calendar.DAY_OF_WEEK);

        if (day ==2)
            day_string = "MON";
        else if (day ==3)
            day_string = "TUE";
        else if (day ==4)
            day_string = "WED";
        else if (day ==5)
            day_string = "THU";
        else if (day ==6)
            day_string = "FRI";

        yoil.setText(day_string);

        if(day == 2 || day==4 || day==6){
            if(hour>=9 && hour<10)
                gyosi_1 = "1st";
            else if(hour>=10 && hour<11)
                gyosi_1 = "2nd";
            else if(hour>=11 && hour<12)
                gyosi_1 = "3rd";
            else if(hour>=12 && hour<13)
                gyosi_1 = "4th";
            else if(hour>=13 && hour<14)
                gyosi_1 = "5th";
            else if(hour>=14 && hour<15)
                gyosi_1 = "6th";
            else if(hour>=15 && hour<16)
                gyosi_1 = "7th";
            else if(hour>=16 && hour<17)
                gyosi_1 = "8th";
            else if(hour>=17 && hour<18)
                gyosi_1 = "9th";
        }else if(day ==3 || day==5){
            if(540<=minute_sum && minute_sum<630)
                gyosi_2 = "1st";
            else if(630<=minute_sum && minute_sum<720)
                gyosi_2 = "2nd";
            else if(720<=minute_sum && minute_sum<810)
                gyosi_2 = "3rd";
            else if(810<=minute_sum && minute_sum<900)
                gyosi_2 = "4th";
            else if(900<=minute_sum && minute_sum<990)
                gyosi_2 = "5th";
            else if(990<=minute_sum && minute_sum<1080)
                gyosi_2 = "6th";
        }




        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = mDatabase.getReference();

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot classSnapshot : dataSnapshot.getChildren()){
                    String key1 = classSnapshot.getKey(); //몇 호인지
                    Log.i("TAG: value is ", String.valueOf(key1));

                    for(DataSnapshot daySnapshot : classSnapshot.getChildren()){

                        String key2 = daySnapshot.getKey(); // 무슨 요일인지
                        Log.i("TAG: value is ", String.valueOf(key2));


                            for(DataSnapshot numSnapshot : daySnapshot.getChildren()) {
                                String key3 = numSnapshot.getKey(); // 몇교시인지
                                Log.i("TAG: value is ", String.valueOf(key3));

                                if (key2.equals(day_string)){
                                    if (key3.equals(gyosi_1)||key3.equals(gyosi_2)) {
                                        long bools = numSnapshot.child("bool").getValue(long.class);
                                        if (bools == 0) {
                                            noclasslist.add(key1);
                                        }
                                    }
                                 }
                            }


                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", "Failed to read value", databaseError.toException());
            }
        });
}


    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTime(){
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd  HH:mm분"));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.refreshbutton) {
            dateNow.setText(getTime());
        }
    }

}

