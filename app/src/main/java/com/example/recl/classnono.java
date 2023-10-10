package com.example.recl;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class classnono extends AppCompatActivity implements View.OnClickListener {
    static boolean calledAlready = false;
    TextView dateNow;
    private ListView listView;
    Button refreshbutton;
    int hour, mhour, minute, minute_sum, day;

    LocalDateTime now = LocalDateTime.now();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classnono);
        dateNow = (TextView) findViewById(R.id.dateNow);
        dateNow.setText(getTime());
        refreshbutton = findViewById(R.id.refreshbutton);
        refreshbutton.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.Listview);
        ArrayList<String> classlist = new ArrayList<>(List.of("113","116","117","118","119","217","218","220","221","222"));
        day = now.getDayOfWeek().getValue();

        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        final MyAdapter items1 = new MyAdapter();
        listView.setAdapter(items1);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = mDatabase.getReference();
        databaseRef.addValueEventListener(new ValueEventListener() {
            //items1.clearItem();
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    for (DataSnapshot classSnapshot : datasnapshot.getChildren()) {
                        String key1 = classSnapshot.getKey(); //몇 호인지
                        for (DataSnapshot daySnapshot : datasnapshot.getChildren()) {
                            String key2 = daySnapshot.getKey(); // 무슨 요일인지
                            for (DataSnapshot numSnapshot : daySnapshot.getChildren()) {
                                String key3 = numSnapshot.getKey(); // 몇교시인지
                                String bools = numSnapshot.child("bool").getValue(String.class);
                                if (bools == "0") {
                                    if(key2.equals(setyoil(day))){
                                        items1.addItem(key1);
                                    }
                                } else {
                                    items1.addItem("사용 가능 한 강의실이 없습니다");
                                }
                                items1.notifyDataSetChanged();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("TAG: ", "Failed to read value", error.toException());
                }
            });
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

    public String setyoil(int dayOfWeek) {
        String day_string = new String();

        if (dayOfWeek ==1)
            day_string = "MON";
        else if (dayOfWeek ==2)
            day_string = "TUE";
        else if (dayOfWeek ==3)
            day_string = "WED";
        else if (dayOfWeek ==4)
            day_string = "THU";
        else if (dayOfWeek ==5)
            day_string = "FRI";

        return day_string;
    }


    public String setgyosi(int dayOfWeek){
        String gyosi = new String();
        hour = now.getHour();
        mhour = hour*60;
        minute = now.getMinute();
        minute_sum = mhour+minute;

        if(dayOfWeek == 1 || dayOfWeek==3 || dayOfWeek==5) {
            if (hour == 9)
                gyosi = "1st";
            else if (hour == 10)
                gyosi = "2nd";
            else if (hour == 11)
                gyosi = "3rd";
            else if (hour == 12)
                gyosi = "4th";
            else if (hour == 13)
                gyosi = "5th";
            else if (hour == 14)
                gyosi = "6th";
            else if (hour == 15)
                gyosi = "7th";
            else if (hour == 16)
                gyosi = "8th";
            else if (hour == 17)
                gyosi = "9th";
        }
        else if(dayOfWeek == 2 || dayOfWeek== 4){
            if(minute_sum >= 540 && minute_sum<630)
                gyosi = "1st";
            else if(minute_sum >= 630 && minute_sum<720)
                gyosi = "2nd";
            else if(minute_sum >= 720 && minute_sum<810)
                gyosi = "3rd";
            else if(minute_sum >= 810 && minute_sum<900)
                gyosi = "4th";
            else if(minute_sum >= 900 && minute_sum<990)
                gyosi = "5th";
            else if(minute_sum >= 990 && minute_sum<1080)
                gyosi = "6th";
        }

        return gyosi;
    }


    public class MyAdapter extends BaseAdapter {

        public ArrayList<MyItem> mItems = new ArrayList<>();

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public MyItem getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        public View getView(final int position, final View convertView, ViewGroup parent) {

            View v = convertView;
            classnono.MyAdapter.ViewHolder holder;

            if (v == null) {
                holder = new classnono.MyAdapter.ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.noclasslist_item, null);

                holder.textView1 = v.findViewById(R.id.classnum);

                v.setTag(holder);
            }else{
                holder = (classnono.MyAdapter.ViewHolder)v.getTag();
            }


            MyItem myItem = getItem(position);
            holder.textView1.setText(myItem.getStr1());
            return v;
        }

        public void addItem(String str1) {

            MyItem mItem = new MyItem();

            mItem.setStr1(str1);

            mItems.add(mItem);
        }

        public void clearItem(){
            mItems.clear();
        }

        public class ViewHolder
        {
            TextView textView1;

        }
    }

}