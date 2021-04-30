package university.project.inyourshoes.Views;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import university.project.inyourshoes.Adapters.QuestionsPagerAdapter;
import university.project.inyourshoes.Fragments.QuestionFiveFragment;
import university.project.inyourshoes.Fragments.QuestionFourFragment;
import university.project.inyourshoes.Fragments.QuestionThreeFragment;
import university.project.inyourshoes.Fragments.QuestionTwoFragment;
import university.project.inyourshoes.Fragments.QuestionOneFragment;

import university.project.inyourshoes.Interfaces.IFragment;
import university.project.inyourshoes.Model.QuestionsRandomFive;
import university.project.inyourshoes.Model.QuestionsRandomFour;
import university.project.inyourshoes.Model.Questions;

import university.project.inyourshoes.Model.UserAnswers;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FiveQs extends AppCompatActivity {

    //Database References for FiveQ's
    DatabaseReference databaseStaticQuestions;
    DatabaseReference databaseRandomQuestionFour;
    DatabaseReference databaseRandomQuestionFive;
    DatabaseReference databaseUserAnswers;

    //Arraylists for Questions on FiveQs, initial 3 questions list dont change
    List<Questions> questionList = new ArrayList<>();
    //Question Four Question List seperated from first 3 and Q5, for ability to select random
    // question set as a q4 question in DB
    List<QuestionsRandomFour> questionFourList = new ArrayList<>();
    //Question Five Question List seperated from first 3 and Q4, for ability to select random
    // question set as a q5 question in DB
    List<QuestionsRandomFive> questionFiveList = new ArrayList<>();

    //ArrayList of answers collected from user input on submit initialisation
    ArrayList<String> answerList = new ArrayList<String>();

    //Variable Initialisation
    ViewPager2 viewPager2;
    FragmentStateAdapter questionsPagerAdapter;
    Button answerSubmitBtn;
    List<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_q);

        //Variables Setting
        viewPager2 = findViewById(R.id.questionPager);
        questionsPagerAdapter = new QuestionsPagerAdapter(this, fragmentList);
        answerSubmitBtn = findViewById(R.id.userAnswerSubmitBtn);


        //Functions for Questions in UI
        getStaticQuestions();
        getQuestionFours();
        getQuestionFives();

        answerSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserAnswers();
            }
        });
    }

    //Setup QuestionList from Firebase Data, First 3 questions are statically the same, question
    // 4 and 5 collected seperately as random indexes of entries in firebase and added to Array,

    public void getStaticQuestions() {
        FirebaseDatabase firebaseDatabase;
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseStaticQuestions = firebaseDatabase.getReference("FiveQ's").child("Static");

        ValueEventListener staticEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Questions questions = ds.getValue(Questions.class);
                    questionList.add(questions);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseStaticQuestions.addValueEventListener(staticEventListener);
    }


    public void getQuestionFours() {
        FirebaseDatabase firebaseDatabase;
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseRandomQuestionFour =
                firebaseDatabase.getReference("FiveQ's").child("Random").child("QuestionFour");

        ValueEventListener staticEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    QuestionsRandomFour questionsRandomFour = ds.getValue(QuestionsRandomFour.class);
                    questionFourList.add(questionsRandomFour);
                }
                Questions questions = new Questions();
                questions.setQuestion((questionFourList.get(getRandomQuestionFour()).getQuestion()));
                questions.setId(4);
                questionList.add(questions);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        databaseRandomQuestionFour.addValueEventListener(staticEventListener);
    }


    public void getQuestionFives() {
        FirebaseDatabase firebaseDatabase;
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseRandomQuestionFive = firebaseDatabase.getReference("FiveQ's").child("Random").child("QuestionFive");

        ValueEventListener staticEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    QuestionsRandomFive questionsRandomFive = ds.getValue(QuestionsRandomFive.class);
                    questionFiveList.add(questionsRandomFive);
                }

                Questions questions = new Questions();
                questions.setQuestion((questionFiveList.get(getRandomQuestionFive()).getQuestion()));
                questions.setId(5);
                questionList.add(questions);
                getFragments();
                viewPager2.setAdapter(questionsPagerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseRandomQuestionFive.addValueEventListener(staticEventListener);

    }


    //Get Random Position from each of the Question four and Question Fives ArrayLists to add to
    // the questionList, alongside the static 1-3 questions

    public int getRandomQuestionFour() {
        Random random = new Random();
        int index = random.nextInt(questionFourList.size());
        return index;
    }

    public int getRandomQuestionFive() {
        Random random = new Random();
        int index = random.nextInt(questionFiveList.size());
        return index;
    }

    //Fragments used for questions and answers input
    private List<Fragment> getFragments() {
        fragmentList.add(QuestionOneFragment.newInstance(questionList.get(0).getQuestion()));
        fragmentList.add(QuestionTwoFragment.newInstance(questionList.get(1).getQuestion()));
        fragmentList.add(QuestionThreeFragment.newInstance(questionList.get(2).getQuestion()));
        fragmentList.add(QuestionFourFragment.newInstance(questionList.get(3).getQuestion()));
        fragmentList.add(QuestionFiveFragment.newInstance(questionList.get(4).getQuestion()));
        return fragmentList;
    }


    public void getUserAnswers() {

        //Getting each uiser answer from the UI
        for (int position = 0; position < fragmentList.size(); position++) {
            if (fragmentList.get(position) instanceof IFragment) {
                if (!TextUtils.isEmpty(((IFragment) fragmentList.get(position)).onQuestionAnswer())) {
                    IFragment fragment = (IFragment) fragmentList.get(position);
                    answerList.add(fragment.onQuestionAnswer());
                }
                else {
                    Toast.makeText(this, "You need to enter text in all 5 question boxes to submit your answers", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, FiveQs.class));

                }
            }
        }

        submitAnswers(answerList);

    }


    //User Answer Collection functionality, and submitting to database
    public void submitAnswers(ArrayList<String> answerList) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        UserAnswers userAnswers = new UserAnswers();
        databaseUserAnswers = FirebaseDatabase.getInstance().getReference("Users").child("user-answers");

        //Each question collected
        for (int i = 0; i < questionList.size(); i++) {
            switch (i) {
                case 0:
                    userAnswers.setQuestionOne(questionList.get(0).getQuestion());
                    userAnswers.setQuestionOneAnswer(answerList.get(0).toString());
                case 1:
                    userAnswers.setQuestionTwo(questionList.get(1).getQuestion());
                    userAnswers.setQuestionTwoAnswer(answerList.get(1).toString());
                case 2:
                    userAnswers.setQuestionThree(questionList.get(2).getQuestion());
                    userAnswers.setQuestionThreeAnswer(answerList.get(2).toString());
                case 3:
                    userAnswers.setQuestionFour(questionList.get(3).getQuestion());
                    userAnswers.setQuestionFourAnswer(answerList.get(3).toString());
                case 4:
                    userAnswers.setQuestionFive(questionList.get(4).getQuestion());
                    userAnswers.setQuestionFiveAnswer(answerList.get(4).toString());
            }
        }

        //getting User AccountId associated with the answers being collected, with the user
        // logged in details. checking for google user, or firebase user
        if (account != null) {
            userAnswers.setUserId(account.getId());
            String id = databaseUserAnswers.push().getKey();
            databaseUserAnswers.child(id).setValue(userAnswers);

            startActivity(new Intent(FiveQs.this, Menu.class));
            Toast.makeText(this, "Answers Submitted Successfully", Toast.LENGTH_SHORT).show();
        }

        if (firebaseUser != null) {
            userAnswers.setUserId(firebaseUser.getUid());
            String id = databaseUserAnswers.push().getKey();
            databaseUserAnswers.child(id).setValue(userAnswers);

            startActivity(new Intent(FiveQs.this, Menu.class));
            Toast.makeText(this, "Answers Submitted Successfully", Toast.LENGTH_SHORT).show();

        }
    }

}



