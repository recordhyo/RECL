package com.example.recl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Mypage extends AppCompatActivity {

    Button buttonlist;
    Button buttonout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        buttonlist = findViewById(R.id.buttonlist);
        buttonout = findViewById(R.id.buttonout);

        buttonlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Mypage.this, Myreserve.class));
            }
        });
        buttonout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Mypage.this, "로그아웃", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Mypage.this, LoginActivity.class));
            }
        });
    }
}
