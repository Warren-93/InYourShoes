package com.example.inyourshoes.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inyourshoes.Interfaces.IFragment;
import com.example.inyourshoes.Model.UserAnswers;

import com.example.inyourshoes.Views.R;




public class QuestionOneFragment extends Fragment {

    IFragment fListener;

    private static final String Extra_QuestionOne = "QuestionOne";
    EditText questionOneAnswerText;
    TextView questionOne;


    UserAnswers userAnswers = new UserAnswers();


    public static QuestionOneFragment newInstance(String question){
        QuestionOneFragment fragmentOne = new QuestionOneFragment();

        Bundle bundle = new Bundle();
        bundle.putString("Extra_QuestionOne", question);
        fragmentOne.setArguments(bundle);
        return fragmentOne;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.question_one, parent, false);
        String question_one = getArguments().getString("Extra_QuestionOne");
        questionOne = rootView.findViewById(R.id.questionOneText);
        questionOneAnswerText = rootView.findViewById(R.id.questionFiveAnswer);
        questionOne.setText(question_one);
        userAnswers.setQuestionOne(question_one);
        return rootView;
    }

    public void onClick(View view){


        String question = questionOneAnswerText.getText().toString();
    }

    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    public interface OnFragmentOneListener {
        void onQuestionOneAnswerUpdated(String questionOneAnswer);
    }

    public void onDetach() {
        super.onDetach();
    }
}
