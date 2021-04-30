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

import university.project.inyourshoes.Model.UserJournal;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Journal extends AppCompatActivity {

    //Variable Initialising
    DatabaseReference databaseJournal;
    TextView dateView;
    EditText journalEntry;
    Button userJournalBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);


        //Database Reference location
        databaseJournal = FirebaseDatabase.getInstance().getReference("Users").child("user" + "-journal");

        //Variable UI Setting
        dateView = findViewById(R.id.dateView);
        journalEntry = findViewById(R.id.journalEntry) ;
        userJournalBtn = findViewById(R.id.userJournalBtn);


        //Get Time and Date for display
        Date currentDate = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
        dateView.setText(formattedDate);

        userJournalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEntry();
            }
        });
    }


    //Collecting user entry, setting userid and getting the journal entry and the date entered to
    // be then sent to database
    private void addEntry(){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userEntry = journalEntry.getEditableText().toString().trim();
        String entryDate = dateView.getText().toString().trim();



        if(!TextUtils.isEmpty(userEntry)) {
            UserJournal userJournal = new UserJournal();

            if (account != null) {

                String id = databaseJournal.push().getKey();

                userJournal.setUserId(account.getId());
                userJournal.setEntry(userEntry);
                userJournal.setDate(entryDate);

                databaseJournal.child(Objects.requireNonNull(id)).setValue(userJournal);

                Toast.makeText(this, "Journal Entry Succesful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Journal.this, Menu.class));
                finish();


            } else if (firebaseUser != null) {

                String id = databaseJournal.push().getKey();

                userJournal.setUserId(firebaseUser.getUid());
                userJournal.setEntry(userEntry);
                userJournal.setDate(entryDate);
                databaseJournal.child(Objects.requireNonNull(id)).setValue(userJournal);

                Toast.makeText(this, "Journal Entry Succesful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Journal.this, Menu.class));
                finish();

            }

        }else{
            Toast.makeText(this, "You need to enter text into the journal box",
                    Toast.LENGTH_SHORT).show();

        }
    }

}