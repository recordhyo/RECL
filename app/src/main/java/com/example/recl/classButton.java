package com.example.recl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class classButton extends AppCompatActivity {

    Button button113;
    Button button116;
    Button button117;
    Button button118;
    Button button119;
    Button button217;
    Button button218;
    Button button220;
    Button button221;
    Button button222;
    Button buttonMypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classbutton);
        Intent intent = getIntent();
        String year = intent.getStringExtra("intyear");
        String month = intent.getStringExtra("intmonth");
        String day = intent.getStringExtra("intday");
        String dayresult = year + "년 "+month+"월 "+day +"일";
        int dayOfWeek = intent.getIntExtra("intyoil",1);
        TextView dateview = (TextView) findViewById(R.id.dateview);
        dateview.setText(dayresult);

        button113 = findViewById(R.id.button113);
        button116 = findViewById(R.id.button116);
        button117 = findViewById(R.id.button117);
        button118 = findViewById(R.id.button118);
        button119 = findViewById(R.id.button119);
        button217 = findViewById(R.id.button217);
        button218 = findViewById(R.id.button218);
        button220 = findViewById(R.id.button220);
        button221 = findViewById(R.id.button221);
        button222 = findViewById(R.id.button222);
        buttonMypage = findViewById(R.id.buttonMypage);

        button113.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classButton.this, MainActivity.class);
                intent.putExtra("classnum", "113");
                intent.putExtra("completedate", dayresult);
                intent.putExtra("yoil", setyoil(dayOfWeek));
                startActivity(intent);
            }
        });

        button116.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classButton.this, MainActivity.class);
                intent.putExtra("classnum", "116");
                intent.putExtra("completedate", dayresult);
                intent.putExtra("yoil", setyoil(dayOfWeek));
                startActivity(intent);
            }
        });

        button117.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classButton.this, MainActivity.class);
                intent.putExtra("classnum", "117");
                intent.putExtra("completedate", dayresult);
                intent.putExtra("yoil", setyoil(dayOfWeek));
                startActivity(intent);
            }
        });

        button118.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classButton.this, MainActivity.class);
                intent.putExtra("classnum", "118");
                intent.putExtra("completedate", dayresult);
                intent.putExtra("yoil", setyoil(dayOfWeek));
                startActivity(intent);
            }
        });

        button119.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classButton.this, MainActivity.class);
                intent.putExtra("classnum", "119");
                intent.putExtra("completedate", dayresult);
                intent.putExtra("yoil", setyoil(dayOfWeek));
                startActivity(intent);
            }
        });

        button217.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classButton.this, MainActivity.class);
                intent.putExtra("classnum", "217");
                intent.putExtra("completedate", dayresult);
                intent.putExtra("yoil", setyoil(dayOfWeek));
                startActivity(intent);
            }
        });

        button218.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classButton.this, MainActivity.class);
                intent.putExtra("classnum", "218");
                intent.putExtra("completedate", dayresult);
                intent.putExtra("yoil", setyoil(dayOfWeek));
                startActivity(intent);
            }
        });
        button220.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classButton.this, MainActivity.class);
                intent.putExtra("classnum", "220");
                intent.putExtra("completedate", dayresult);
                intent.putExtra("yoil", setyoil(dayOfWeek));
                startActivity(intent);
            }
        });
        button221.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classButton.this, MainActivity.class);
                intent.putExtra("classnum", "221");
                intent.putExtra("completedate", dayresult);
                intent.putExtra("yoil", setyoil(dayOfWeek));
                startActivity(intent);
            }
        });
        button222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classButton.this, MainActivity.class);
                intent.putExtra("classnum", "222");
                intent.putExtra("completedate", dayresult);
                intent.putExtra("yoil", setyoil(dayOfWeek));
                startActivity(intent);
            }
        });

        buttonMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classButton.this, Mypage.class);
                startActivity(intent);
            }
        });


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
}


