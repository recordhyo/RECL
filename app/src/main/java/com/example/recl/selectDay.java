package com.example.recl;

import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class selectDay extends AppCompatActivity {
    Button buttonMypage;
    public CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectday);
        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent(selectDay.this, classButton.class);
                intent.putExtra("intyear", Integer.toString(year));
                intent.putExtra("intmonth", Integer.toString(month));
                intent.putExtra("intday", Integer.toString(dayOfMonth));
                Calendar calendar = Calendar.getInstance();
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                intent.putExtra("intyoil", dayOfWeek);
                startActivity(intent);
            }
        });
        buttonMypage = findViewById(R.id.buttonMypage);
        buttonMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selectDay.this, Mypage.class));
            }
        });
    }


}