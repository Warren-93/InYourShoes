package university.project.inyourshoes.Views;



import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import university.project.inyourshoes.Adapters.EmojiAdapter;
import university.project.inyourshoes.Interfaces.IEmoji;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import university.project.inyourshoes.Model.Emoji;
import university.project.inyourshoes.Model.UserEmotions;


public class MoodTracker extends AppCompatActivity implements IEmoji {

    //Variables Initialisation
    TextView welcomeText, dateView;
    CardView emojiButtonCard;
    RecyclerView recyclerView;
    DatabaseReference databaseEmojis;
    FirebaseDatabase firebaseDatabase;
    List<Emoji> emojiList = new ArrayList<>();
    DatabaseReference databaseEmotions;
    EmojiAdapter emojiAdapter;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_tracker);


        //Variables Instantiating
        welcomeText = findViewById(R.id.welcomeText);
        dateView = findViewById(R.id.dateView);
        emojiButtonCard = findViewById(R.id.emojiButtonCard);
        recyclerView = findViewById(R.id.recyclerView);


        //Layout manager setup for grid layout and recycler view widget
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Adapter reference and setup
        emojiAdapter = new EmojiAdapter(emojiList, this);

        //Get Time and Date for display

        String welcome = "How are you feeling?";

        welcomeText.setText(welcome);
        getCurrentDate();
        getEmojis();

    }


    private void getCurrentDate() {
        Date currentDate = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
        dateView.setText(formattedDate);
    }

    public void getEmojis() {

        //Retrieve Data from Firebase, and set up Image Adapter display for Emoticon Buttons
        databaseEmojis = firebaseDatabase.getReference("Emojis");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                emojiList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Emoji emoji = ds.getValue(Emoji.class);
                    emojiList.add(emoji);
                }
                recyclerView.setAdapter(emojiAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MoodTracker.this, "canceled, process failed",
                        Toast.LENGTH_SHORT).show();
            }
        };
        databaseEmojis.addValueEventListener(eventListener);

    }

    @Override
    public void onClick(View view, int position) {
        trackEmotion(position);
    }

    private void trackEmotion(int position){

        //Emoji emotion tracker getting the user data and the clicked emnotion for collection and
        // sent to database, setup for both google user and firebase user

        //setting user settings for firebase and google user
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //Instantiates user emotions object
        UserEmotions userEmotions = new UserEmotions();
        //Database reference for location to send data collected
        databaseEmotions = FirebaseDatabase.getInstance().getReference("Users").child("user-emotions");

        if(!TextUtils.isEmpty(emojiList.get(position).getUnicode())) {

            if (account != null) {
                userEmotions.setUserId(account.getId());
                userEmotions.setUnicode(emojiList.get(position).getUnicode());
                userEmotions.setUnicodeName(emojiList.get(position).getName());
                userEmotions.setDate(dateView.getText().toString());

                String id = databaseEmotions.push().getKey();
                databaseEmotions.child(id).setValue(userEmotions);

                Toast.makeText(this, "Emotion selected Succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MoodTracker.this, Menu.class));
                finish();

            } else if (firebaseUser != null) {

                userEmotions.setUserId(firebaseUser.getUid());
                userEmotions.setUnicode(emojiList.get(position).getUnicode());
                userEmotions.setUnicodeName(emojiList.get(position).getName());
                userEmotions.setDate(dateView.getText().toString());

                String id = databaseEmotions.push().getKey();
                databaseEmotions.child(id).setValue(userEmotions);
                Toast.makeText(this, "Emotion selected Succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MoodTracker.this, Menu.class));
                finish();

            }
        }
        else {
            Toast.makeText(this, "Emotion selected unsuccesfully", Toast.LENGTH_SHORT).show();

        }
    }
}

