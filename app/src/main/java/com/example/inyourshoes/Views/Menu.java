package com.example.inyourshoes.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class Menu extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    Button logoutBtn, moodBtn, journalBtn, chatBtn, qBtn, infoBtn;
    TextView userText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //set up welcome message
        userText = findViewById(R.id.userText);

        //Set Up Logout from Google
        logoutBtn = findViewById(R.id.logOutBtn);
        logoutBtn.setOnClickListener(this::onClick);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Menu.this);


        //Configure Menu Buttons
        moodBtn = findViewById(R.id.moodBtn);
        moodBtn.setOnClickListener(this::onClick);
        journalBtn = findViewById(R.id.journalBtn);
        journalBtn.setOnClickListener(this::onClick);
        chatBtn = findViewById(R.id.chatBtn);
        chatBtn.setOnClickListener(this::onClick);
        qBtn = findViewById(R.id.fiveQsBtn);
        qBtn.setOnClickListener(this::onClick);
        infoBtn = findViewById(R.id.infoBtn);
        infoBtn.setOnClickListener(this::onClick);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(account != null)
            userText.setText(String.format("Welcome, %s", account.getDisplayName()));
        else if( firebaseUser != null)
            userText.setText(String.format("Welcome, %s", firebaseUser.getDisplayName()));
    }

    @SuppressLint("NonConstantResourceId")
    private void onClick(View view) {
        switch (view.getId()){
            case R.id.moodBtn:
                startActivity(new Intent(Menu.this, MoodTracker.class));
                finish();
                break;
            case R.id.journalBtn:
                startActivity(new Intent(Menu.this, Journal.class));
                finish();
                break;
            case R.id.chatBtn:
                /*startActivity(new Intent(Menu.this, Chat.class));*/
                Toast.makeText(this, "This Feature is not currently Available", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.fiveQsBtn:
                startActivity(new Intent(Menu.this, FiveQs.class));
                finish();
                break;
            case R.id.infoBtn:
                /*startActivity(new Intent(Menu.this, MentalHealthInfo.class));*/
                Toast.makeText(this, "Not currently Available", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.logOutBtn:
                logOut();
                break;
        }
    }

    private void logOut(){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (account != null) {
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this, task -> {
                        Toast.makeText(Menu.this, "Successfully signed out", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Menu.this, MainActivity.class));
                        finish();
                    });
        } else if (firebaseUser != null) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Menu.this, MainActivity.class));
            finish();
        }
    }
}