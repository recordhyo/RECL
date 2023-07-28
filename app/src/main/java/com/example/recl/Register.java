package com.example.recl;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");
    private static  final String TAG = "Register";

    private FirebaseAuth  firebaseAuth;
    private FirebaseUser currentUser;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseRef = mDatabase.getReference();

    EditText editTextEmail2;
    EditText editTextPassword2;
    EditText editTextName;

    String email2 = "";
    String password2 = "";
    String username="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail2 = findViewById(R.id.et_email2);
        editTextPassword2 = findViewById(R.id.et_password2);
        editTextName = findViewById(R.id.et_name);

        Button Btn_signup2 = findViewById(R.id.btn_signup2);

        Btn_signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               registerUser();
            }
        });


    }
    public void AddUser(String name, String email) {

        final DatabaseReference newReference = databaseRef.child("User");

        String id100 = currentUser.getUid();
        // String name100 = "";
        //  String email100 = "";

        //   name100 = editTextName.getText().toString();
        //   email100 = editTextEmail2.getText().toString();

        User user = new User(name, email);

        newReference.child(id100).setValue(user);
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User value = dataSnapshot.getValue(User.class);
                Log.d(TAG, value.toString());

                String newUserKey = newReference.getKey();
                Log.d(TAG, "newUserKey : " + newUserKey);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", "Failed to read value", databaseError.toException());
            }
        });
    }




    private boolean isValidEmail() {
        if (email2.isEmpty()) {
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email2).matches()) {
            return false;
        } else {
            return true;
        }
    }
    private boolean isValidPassword() {
        if (password2.isEmpty()) {
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password2).matches()) {
            return false;
        } else {
            return true;
        }
    }
    private void registerUser() {

        username = editTextName.getText().toString();
        email2 = editTextEmail2.getText().toString();
        password2 = editTextPassword2.getText().toString();

        if (isValidEmail() && isValidPassword()) {
            firebaseAuth.createUserWithEmailAndPassword(email2, password2)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                try {
                                    throw task.getException();
                                }catch(FirebaseAuthWeakPasswordException e) {
                                    Toast.makeText(Register.this,"비밀번호를 다시 입력해주세요." ,Toast.LENGTH_SHORT).show();
                                }catch (FirebaseAuthUserCollisionException e) {
                                    Toast.makeText(Register.this, "이미 존재하는 이메일입니다.", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Toast.makeText(Register.this, "다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                // 회원가입 성공
                                currentUser = firebaseAuth.getCurrentUser();
                                AddUser(username, email2);

                                //databaseRef.child("User").push().setValue(currentUser.getUid());
                                //currentUser.getDisplayName();
                                Toast.makeText(Register.this, "회원가입 성공", Toast.LENGTH_SHORT).show();

                                Intent button1Intent = new Intent(Register.this,LoginActivity.class);
                                Register.this.startActivity(button1Intent);
                            }
                        }
                    });
        }
        else{
            Toast.makeText(Register.this, "다시 확인해라.", Toast.LENGTH_SHORT).show();
        }



    }
}
