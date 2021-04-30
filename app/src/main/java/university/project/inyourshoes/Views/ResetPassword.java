package university.project.inyourshoes.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {


    EditText resetEmail;
    Button resetPasswordBtn;

    //Reset password functionality
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        resetEmail = findViewById(R.id.resetEmail);
        resetPasswordBtn  = findViewById(R.id.resetPasswordBtn);


        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = resetEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(ResetPassword.this,
                            "Please enter email address",
                            Toast.LENGTH_SHORT).show();

                }else{
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(ResetPassword.this, "Email sent successfully to reset your password", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ResetPassword.this, Home.class));
                            finish();
                        }
                    });

                }
            }
        });



    }
}