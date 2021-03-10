package com.example.inyourshoes.Views;


;
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
import com.example.inyourshoes.Adapters.EmojiAdapter;
import com.example.inyourshoes.Interfaces.IEmoji;
import com.example.inyourshoes.Model.Emoji;
import com.example.inyourshoes.Model.UserEmotions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
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
import java.util.Objects;


public class MoodTracker extends AppCompatActivity implements IEmoji {

    TextView welcomeText, dateView;
    CardView emojiButtonCard;
    RecyclerView recyclerView;
    DatabaseReference databaseEmojis;
    FirebaseDatabase firebaseDatabase;
    final private static List<Emoji> emojiList = new ArrayList<>();
    DatabaseReference databaseEmotions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_tracker);

        welcomeText = findViewById(R.id.welcomeText);
        dateView = findViewById(R.id.dateView);
        emojiButtonCard = findViewById(R.id.emojiButtonCard);
        recyclerView = findViewById(R.id.recyclerView);
        EmojiAdapter emojiAdapter = new EmojiAdapter(emojiList, this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        //Get Time and Date for display
        Date currentDate = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
        dateView.setText(formattedDate);

        firebaseDatabase = FirebaseDatabase.getInstance();

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

            }
        };
        databaseEmojis.addValueEventListener(eventListener);
    }



    @Override
    public void onClick(View view, int position) {
        trackEmotion(position);
    }

    private void trackEmotion(int position){

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        UserEmotions userEmotions = new UserEmotions();
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

            } else if (firebaseUser != null) {

                userEmotions.setUserId(firebaseUser.getUid());
                userEmotions.setUnicode(emojiList.get(position).getUnicode());
                userEmotions.setUnicodeName(emojiList.get(position).getName());
                userEmotions.setDate(dateView.getText().toString());

                String id = databaseEmotions.push().getKey();
                databaseEmotions.child(id).setValue(userEmotions);
                Toast.makeText(this, "Emotion selected Succesfully", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Emotion selected unsuccesfully", Toast.LENGTH_SHORT).show();

        }
    }

}

