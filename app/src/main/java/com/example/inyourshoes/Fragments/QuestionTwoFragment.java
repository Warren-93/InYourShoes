package com.example.inyourshoes.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inyourshoes.Views.R;

public class QuestionTwoFragment extends Fragment {


    private static final String Extra_QuestionTwo = "QuestionTwo";

    TextView questionTwo;
    EditText questionTwoAnswer;

    public static QuestionTwoFragment newInstance(String question){

        QuestionTwoFragment fragmentTwo = new QuestionTwoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Extra_QuestionTwo", question);
        fragmentTwo.setArguments(bundle);
        return fragmentTwo;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.question_two, container, false);
        String question_Two = getArguments().getString("Extra_QuestionTwo");
        questionTwo = (TextView) rootView.findViewById(R.id.questionTwoText);
        questionTwoAnswer = rootView.findViewById(R.id.questionTwoAnswer);
        questionTwo.setText(question_Two);
        return rootView;
    }


    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }
    public interface OnFragmentTwoListener {
        void getAnswerString(String answer);
    }

    public void getAnswerString(){
        questionTwoAnswer.getText().toString();
    }
    public void onDetach() {
        super.onDetach();
    }

}