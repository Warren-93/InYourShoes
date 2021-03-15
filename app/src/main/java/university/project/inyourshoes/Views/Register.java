package university.project.inyourshoes.Views;

import university.project.inyourshoes.Model.Users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Register extends AppCompatActivity {

    private EditText regFirstName, regSurname, regEmail, regPassword, regPasswordReEnter;
    private Button regButton, regBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        regBackBtn = findViewById(R.id.regBackBtn);

        regFirstName = findViewById(R.id.regFirstName);
        regSurname = findViewById(R.id.regSurname);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regPasswordReEnter = findViewById(R.id.regPasswordReEnter);
        regButton = findViewById(R.id.regBtn);



        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    registerUser();

            }
        });

        regBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Home.class));
            }
        });

    }

    private void registerUser() {


        String firstName = regFirstName.getEditableText().toString().trim();
        String surname = regSurname.getEditableText().toString().trim();
        String email = regEmail.getEditableText().toString().trim();
        String password = regPassword.getEditableText().toString().trim();
        String passwordReEnter = regPasswordReEnter.getEditableText().toString().trim();



            if (!TextUtils.isEmpty(firstName) || !TextUtils.isEmpty(surname) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(passwordReEnter))  {
                if (password == passwordReEnter) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Users user;
                                    user = new Users(firstName, surname, email);
                                    FirebaseDatabase.getInstance().getReference("Users").child("user-details")
                                            .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Register.this, "User Has been registered Successfully", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(Register.this, SignIn.class));
                                                finish();
                                            } else {
                                                Toast.makeText(Register.this, "Regstration Unsuccessful", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                            }
                        });

            }else {
                    Toast.makeText(this, "Passwords entered must match", Toast.LENGTH_SHORT).show();
                }
        } else {
            Toast.makeText(this, "All Fields Must be filled in", Toast.LENGTH_SHORT).show();

        }
    }
}
