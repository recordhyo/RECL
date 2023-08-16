package com.example.recl;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static  final String TAG = "MainActivity";
    int hour, mhour, minute, minute_sum, day;
    LocalDateTime now = LocalDateTime.now();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    private ListView listView;
    TextView classnum;
    String KEY100 = "";
    String KEY200 = "";
    String KEY300 = "";
    String subject="";
    ArrayList<String> classlist = new ArrayList<>(List.of("113","116","117","118","119","217","218","220","221","222"));

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseRef = mDatabase.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listview1);
        classnum = (TextView)findViewById(R.id.classnum);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        final MyAdapter items1 = new MyAdapter();

        Intent comingintent = getIntent();

        final String class_num = comingintent.getStringExtra("classnum");
        final String yoil = comingintent.getStringExtra("yoil");
        classnum.setText(class_num);


        listView.setAdapter(items1);
        // readUsers();

        for(String classnum : classlist) {
            items1.clearItem();
            databaseRef.child(classnum).addValueEventListener(new ValueEventListener() {
                String key3_twin = "";

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot daySnapshot : snapshot.getChildren()) {
                        String key2 = daySnapshot.getKey(); // 무슨 요일인지
                        for (DataSnapshot numSnapshot : daySnapshot.getChildren()) {
                            String key3 = numSnapshot.getKey(); // 몇교시인지
                            if (classnum.equals(class_num)) {
                                if (key2.equals(yoil)) {
                                    String bools = numSnapshot.child("bool").getValue(String.class);
                                    String okay = "";
                                    if (bools.equals("0")) {
                                        key3_twin = key3;
                                        okay = "신청 가능";
                                        KEY100 = classnum;
                                        KEY200 = key2;
                                    } else {
                                        key3_twin = key3;
                                        okay = "신청 불가";
                                    }
                                    items1.addItem(key3, okay);
                                }
                            }
                        }
                        items1.notifyDataSetChanged();
                    }
                }
                    @Override
                    public void onCancelled (@NonNull DatabaseError error){
                        Log.w("TAG: ", "Failed to read value", error.toException());
                    }
            });
        }

/*                    String key3_twin = "";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                items1.clearItem();
                for(DataSnapshot classSnapshot : dataSnapshot.getChildren()){
                    String key1 = classSnapshot.getKey(); //몇 호인지
                    for(DataSnapshot daySnapshot : classSnapshot.getChildren()){
                        String key2 = daySnapshot.getKey(); // 무슨 요일인지
                        for(DataSnapshot numSnapshot : daySnapshot.getChildren()) {
                            String key3 = numSnapshot.getKey(); // 몇교시인지
                            if(key1.equals(class_num)) {
                                if (key2.equals(yoil)) {
                                    String bools = numSnapshot.child("bool").getValue(String.class);
                                    String okay = "";
                                    key3_twin = key3;
                                    if (bools == "0") {
                                        okay = "신청 가능";
                                        KEY100 = key1;
                                        KEY200 = key2;
                                    } else {
                                        okay = "신청 불가";
                                    }
                                    items1.addItem(key3, okay);
                                }
                            }

                        }
                    }
                }
                items1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", "Failed to read value", databaseError.toException());
            }
        });
*/
    }


    private class MyAdapter extends BaseAdapter {

        private ArrayList<MyItem> mItems = new ArrayList<>();
        private int mSelectedRadioPosition;
        private RadioButton mLastSelectedRadioButton;
        private Button re_button;

        @Override
        public int getCount() {
            System.out.println(mItems.size());
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
            ViewHolder holder;

            if (v == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.list_item, null);

                holder.radioButton = (RadioButton)v.findViewById(R.id.radiobutton1);
                holder.textView1 = (TextView) v.findViewById(R.id.gyosi_text);
                holder.textView2 = (TextView) v.findViewById(R.id.re_text);

                v.setTag(holder);
            }else{
                holder = (ViewHolder)v.getTag();

                if(mSelectedRadioPosition == position){
                    holder.radioButton.setChecked(true);
                }else{
                    holder.radioButton.setChecked(false);
                }
            }

            //RadioGroup rbutton = (RadioGroup) v.findViewById(R.id.radiobutton1);

            MyItem myItem = getItem(position);

            holder.textView1.setText(myItem.getStr1());
            holder.textView2.setText(myItem.getStr2());
            re_button = (Button)findViewById(R.id.re_button);

            holder.radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mSelectedRadioPosition == position){
                        return;
                    }
                    mSelectedRadioPosition = position;

                    if(mLastSelectedRadioButton != null){
                        mLastSelectedRadioButton.setChecked(false);
                    }
                    mLastSelectedRadioButton = (RadioButton) v;

                    notifyDataSetChanged();
                }
            });

            final MyItem myItem2 = getItem(mSelectedRadioPosition);

            re_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(myItem2.getStr2().equals("신청 불가"))
                        Toast.makeText(MainActivity.this, "신청 불가능한 시간입니다.", Toast.LENGTH_SHORT).show();

                        //if(myItem2.getStr2().equals("신청 가능")) {
                    else{
                        KEY300 = myItem2.getStr1();
                        databaseRef.child(KEY100).child(KEY200).child(KEY300).child("bool").setValue("1");
                        databaseRef.child(KEY100).child(KEY200).child(KEY300).child("recl_email").setValue(currentUser.getEmail());
                        //  readUsers();
                        String recl_email = currentUser.getEmail();
                        // String recl_name2 =  recl_name ;
                        String recl_gyosi = KEY300;
                        String recl_class = KEY100;

                        Intent intent = new Intent(MainActivity.this, CompleteActivity.class);

                        //  intent.putExtra("recl_name",recl_name2);
                        intent.putExtra("recl_email",recl_email);
                        intent.putExtra("recl_class",recl_class);
                        intent.putExtra("recl_gyosi",recl_gyosi);



                        MainActivity.this.startActivity(intent);

                    }



                    //Toast.makeText(MainActivity.this,myItem2.getStr1(),Toast.LENGTH_SHORT).show();
                }
            });
            return v;
        }

        public void addItem(String str1, String str2) {

            MyItem mItem = new MyItem();

            mItem.setStr1(str1);
            mItem.setStr2(str2);

            mItems.add(mItem);
        }

        public void clearItem(){
            mItems.clear();
        }

        public class ViewHolder
        {
            TextView textView1;
            TextView textView2;
            RadioButton radioButton;
        }
    }
}
