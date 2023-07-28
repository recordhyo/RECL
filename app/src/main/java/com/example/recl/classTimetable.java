package com.example.recl;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class classTimetable extends AppCompatActivity {

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
        setContentView(R.layout.activity_classtimetable);

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
                Intent intent = new Intent(classTimetable.this, TimeTable.class);
                intent.putExtra("classnum", "113");
                startActivity(intent);
            }
        });

        button116.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classTimetable.this, TimeTable.class);
                intent.putExtra("classnum", "116");
                startActivity(intent);
            }
        });

        button117.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classTimetable.this, TimeTable.class);
                intent.putExtra("classnum", "117");
                startActivity(intent);
            }
        });

        button118.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classTimetable.this, TimeTable.class);
                intent.putExtra("classnum", "118");
                startActivity(intent);
            }
        });

        button119.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classTimetable.this, TimeTable.class);
                intent.putExtra("classnum", "119");
                startActivity(intent);
            }
        });

        button217.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classTimetable.this, TimeTable.class);
                intent.putExtra("classnum", "217");
                startActivity(intent);
            }
        });

        button218.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classTimetable.this, TimeTable.class);
                intent.putExtra("classnum", "218");
                startActivity(intent);
            }
        });
        button220.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classTimetable.this, TimeTable.class);
                intent.putExtra("classnum", "220");
                startActivity(intent);
            }
        });
        button221.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classTimetable.this, TimeTable.class);
                intent.putExtra("classnum", "221");
                startActivity(intent);
            }
        });
        button222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classTimetable.this, TimeTable.class);
                intent.putExtra("classnum", "222");
                startActivity(intent);
            }
        });

        buttonMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(classTimetable.this, Mypage.class);
                startActivity(intent);
            }
        });


}
}