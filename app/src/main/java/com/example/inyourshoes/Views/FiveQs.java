package com.example.inyourshoes.Views;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inyourshoes.Adapters.QuestionsPagerAdapter;
import com.example.inyourshoes.Fragments.QuestionFiveFragment;
import com.example.inyourshoes.Fragments.QuestionFourFragment;
import com.example.inyourshoes.Fragments.QuestionThreeFragment;
import com.example.inyourshoes.Fragments.QuestionTwoFragment;
import com.example.inyourshoes.Fragments.QuestionOneFragment;

import com.example.inyourshoes.Interfaces.IFragment;
import com.example.inyourshoes.Model.QuestionsRandomFive;
import com.example.inyourshoes.Model.QuestionsRandomFour;
import com.example.inyourshoes.Model.Questions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FiveQs extends AppCompatActivity  {


    private String questionOneAnswer;
    private String questionTwoAnswer;
    private String questionThreeAnswer;
    private String questionFourAnswer;
    private String questionFiveAnswer;

    //Database References for FiveQ's

    DatabaseReference databaseStaticQuestions;
    DatabaseReference databaseRandomQuestionFour;
    DatabaseReference databaseRandomQuestionFive;
    DatabaseReference databaseUserAnswers;

    //
    List<Questions> questionList = new ArrayList<>();
    List<QuestionsRandomFour> questionFourList = new ArrayList<>();
    List<QuestionsRandomFive> questionFiveList = new ArrayList<>();
    ArrayList  questionAnswers = new ArrayList();


    ViewPager2 viewPager2;
    FragmentStateAdapter questionsPagerAdapter;
    Button answerSubmitBtn;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> answerList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_q);

        viewPager2 = findViewById(R.id.questionPager);
        questionsPagerAdapter = new QuestionsPagerAdapter(this, fragmentList);
        answerSubmitBtn = findViewById(R.id.userAnswerSubmitBtn);

        getStaticQuestions();
        getQuestionFours();
        getQuestionFives();


        answerSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                getUserAnswers();
                submitAnswers(position);
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

    public int getRandomQuestionFour(){
        Random random = new Random();
        int index = random.nextInt(questionFourList.size());
        return index;
    }

    public int getRandomQuestionFive(){
        Random random = new Random();
        int index = random.nextInt(questionFiveList.size());
        return index;
    }

    private List<Fragment> getFragments(){
        fragmentList.add(QuestionOneFragment.newInstance(questionList.get(0).getQuestion()));
        fragmentList.add(QuestionTwoFragment.newInstance(questionList.get(1).getQuestion()));
        fragmentList.add(QuestionThreeFragment.newInstance(questionList.get(2).getQuestion()));
        fragmentList.add(QuestionFourFragment.newInstance(questionList.get(3).getQuestion()));
        fragmentList.add(QuestionFiveFragment.newInstance(questionList.get(4).getQuestion()));
       return fragmentList;
    }


    public void getUserAnswers(){


        for(int position = 0; position < fragmentList.size(); position++ ){
            if(fragmentList.get(position) instanceof IFragment){
                IFragment fragment = (IFragment) fragmentList.get(position);
                answerList.add(fragment.onQuestionAnswer());
            }
        }
    }


    public void submitAnswers(int position){
        Toast.makeText(this, , Toast.LENGTH_LONG).show();
    }

}



