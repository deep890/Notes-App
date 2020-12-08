package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_password extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        EditText email = findViewById(R.id.forgotEmail);
        Button go = findViewById(R.id.Next);
        int black = android.R.color.black;
        go.setBackgroundColor(black);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EMAIL = email.getText().toString().trim();
                if (TextUtils.isEmpty(EMAIL)){
                    Toast.makeText(getApplicationContext(), "pls enter your email ID", Toast.LENGTH_LONG).show();
                    return;

                }

                mAuth.sendPasswordResetEmail(EMAIL)
                        .addOnCompleteListener(Forgot_password.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    // this IF loop is working but there is no toast coming on the screen
                                    Toast.makeText(Forgot_password.this, "mail sent", Toast.LENGTH_LONG).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Forgot_password.this, "email id doesn't exist",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });



            }
        });
    }
}