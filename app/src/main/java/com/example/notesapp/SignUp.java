package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        Button btn = findViewById(R.id.button);
        int black = android.R.color.black;
        btn.setBackgroundColor(black);
        Button btLogin = findViewById(R.id.toLoginPage) ;
        btLogin.setBackgroundColor(black);
        EditText email = findViewById(R.id.emailID);
        EditText passcode = findViewById(R.id.password);
        EditText confirm_pass = findViewById(R.id.confirm_password);



        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String EMAIL = email.getText().toString().trim();
                String PASS = passcode.getText().toString().trim();
                String CPASS = confirm_pass.getText().toString().trim();
                if (TextUtils.isEmpty(EMAIL)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_LONG).show();
                    return;
                }// toast - pop up message
                if (TextUtils.isEmpty(PASS)) {
                    Toast.makeText(getApplicationContext(), "pls Enter password", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(CPASS)) {
                    Toast.makeText(getApplicationContext(), "pls confirm your password", Toast.LENGTH_LONG).show();
                    return;
                }
                if (PASS.length() < 6){
                    Toast.makeText(getApplicationContext(), "pls make your password more than 6 digits", Toast.LENGTH_LONG).show();
                    return;

                }
                if( ! (PASS.equals(CPASS))){
                    Toast.makeText(getApplicationContext(), "your password and confirm password are not matching", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(EMAIL , PASS)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
//
//                                    // Toast.makeText(getApplicationContext(), "succesfully signed up", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(SignUp.this, SignIn.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUp.this, "Authentication failed.",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }


        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });



    }
}