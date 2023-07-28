package com.example.recl;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseRef = mDatabase.getReference();

    EditText editTextEmail;
    EditText editTextPassword;

    String email = "";
    String password = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        Button Btn_signin = findViewById(R.id.btn_signin);
        Button Btn_signup = findViewById(R.id.btn_signup);

        Btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlogin();
            }
        });
        Btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rintent = new Intent(LoginActivity.this, Register.class);
                LoginActivity.this.startActivity(rintent);
            }
        });
    }


    private boolean isValidEmail() {
        if (email.isEmpty()) {
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        } else {
            return true;
        }
    }
    private boolean isValidPassword() {
        if (password.isEmpty()) {
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            return false;
        } else {
            return true;
        }
    }
    /*private void registerUser() {

        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();


        if (isValidEmail() && isValidPassword()) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                try {
                                    throw task.getException();
                                }catch(FirebaseAuthWeakPasswordException e) {
                                    Toast.makeText(LoginActivity.this,"비밀번호를 다시 입력해주세요." ,Toast.LENGTH_SHORT).show();
                                }catch (FirebaseAuthUserCollisionException e) {
                                    Toast.makeText(LoginActivity.this, "이미 존재하는 이메일입니다.", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Toast.makeText(LoginActivity.this, "다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                // 회원가입 성공
                                currentUser = firebaseAuth.getCurrentUser();
                                //currentUser.getDisplayName();


                                Toast.makeText(LoginActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();

                                Intent button1Intent = new Intent(LoginActivity.this,classButton.class);
                                LoginActivity.this.startActivity(button1Intent);
                            }
                        }
                    });
        }
        else{
            Toast.makeText(LoginActivity.this, "다시 확인해라.", Toast.LENGTH_SHORT).show();
        }
    }*/
    private void userlogin(){
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        if(isValidEmail() && isValidPassword()) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                // 로그인 실패
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthInvalidUserException e) {
                                    Toast.makeText(LoginActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Toast.makeText(LoginActivity.this, "다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // 로그인 성공
                                currentUser = firebaseAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(LoginActivity.this, mainButton.class));
                                finish();
                            }
                        }
                    });
        }
        else{
            Toast.makeText(LoginActivity.this, "다시 확인해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

}