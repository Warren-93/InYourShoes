package com.example.inyourshoes.Views;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inyourshoes.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity {


    Button signInButton, regBtn, resetBtn;
    private static final int RC_SIGN_IN = 100;
    private GoogleSignInClient mGoogleSignInClient;
    SignInButton signInWithGoogleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Views
        signInWithGoogleButton = findViewById(R.id.google_sign_in_button);
        signInWithGoogleButton.setOnClickListener(this::onClick);

        //Set up Sign in With Google Button

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // Set the dimensions of the sign-in button.
        SignInButton signInWithGoogleButton = findViewById(R.id.google_sign_in_button);
        signInWithGoogleButton.setSize(SignInButton.SIZE_STANDARD);


        //Set Up Register/Sign In with User Email and Password UserJournal
        signInButton = (Button)findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this::onClick);

        regBtn = findViewById(R.id.regBtn);
        regBtn.setOnClickListener(this::onClick);

        resetBtn = findViewById(R.id.resetButton);
        resetBtn.setOnClickListener(this::onClick);

    }

    private void onClick(View view) {
        switch (view.getId()){
            case R.id.signInButton:
                startActivity(new Intent(MainActivity.this, SignIn.class));
                finish();
                break;
            case R.id.regBtn:
                startActivity(new Intent(MainActivity.this, Register.class));
                finish();
                break;
            case R.id.google_sign_in_button:
                signInWithGoogle();
                break;
        }
    }

    
    private void signInWithGoogle() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            startActivity(new Intent(MainActivity.this, Menu.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            startActivity(new Intent(MainActivity.this, Menu.class));
        }
        super.onStart();
    }

}