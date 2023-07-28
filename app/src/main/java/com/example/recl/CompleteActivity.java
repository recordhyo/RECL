package com.example.recl;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CompleteActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    String name = "";

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseRef = mDatabase.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        final TextView recl_name = findViewById(R.id.recl_name);
        TextView recl_email = findViewById(R.id.recl_email);
        TextView recl_class = findViewById(R.id.recl_class);
        TextView recl_gyosi = findViewById(R.id.recl_gyosi);
        TextView recl_date = findViewById(R.id.recl_date);
       // final TextView a = findViewById(R.id.ggggg);

        databaseRef.child("User").addValueEventListener(new ValueEventListener() {
            String name100 = "";
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                   String key = snapshot.getKey();
                    Log.i("TAG: value is ", String.valueOf(key));
                    if (key.equals(currentUser.getUid())){
                        //
                        name100 = snapshot.child("username").getValue(String.class);
                        name = name100;
                        recl_name.setText(name100);
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w("TAG: ", "Failed to read value", databaseError.toException());

            }
        });

        Intent intent = getIntent();

       // String name = intent.getStringExtra("recl_name");

        //recl_name.setText(name);
        String email = intent.getStringExtra("recl_email");
        recl_email.setText(email);

        String classnum = intent.getStringExtra("recl_class");
        recl_class.setText(classnum);

        String gyosi = intent.getStringExtra("recl_gyosi");
        recl_gyosi.setText(gyosi);


        //a.setText(name);
    }
}
