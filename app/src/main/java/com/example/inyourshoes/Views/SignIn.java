package com.example.inyourshoes.Views;

import com.example.inyourshoes.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    private EditText signInPassword, signInEmail;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInEmail = findViewById(R.id.signInEmail);
        signInPassword = findViewById(R.id.signInPassword);
        Button signInWithEmailButton = findViewById(R.id.signInWithEmailButton);

        signInWithEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login(){
        mAuth =  FirebaseAuth.getInstance();
        String email = signInEmail.getEditableText().toString().trim();
        String password = signInPassword.getEditableText().toString().trim();


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignIn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignIn.this, Menu.class));
                    finish();
                } else{
                    Toast.makeText(SignIn.this, "Failed to Login!!!, Please Check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}