package com.example.inyourshoes.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inyourshoes.Views.R;

public class QuestionThreeFragment extends Fragment {

    private static final String Extra_QuestionThree = "QuestionThree";

    public static QuestionThreeFragment newInstance(String question){

        QuestionThreeFragment fragmentThree = new QuestionThreeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Extra_QuestionThree", question);
        fragmentThree.setArguments(bundle);
        return fragmentThree;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.question_three, container, false);

        String question_Three = getArguments().getString("Extra_QuestionThree");
        TextView questionThree = (TextView) rootView.findViewById(R.id.questionThreeText);
        questionThree.setText(question_Three);


        return rootView;

    }



    public void onClick(View view){
        String question = questionThreeAnswerText.getText().toString();
    }

    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    public interface OnFragmentThreeListener {
        void onQuestionThreeAnswerUpdated(String questionOneAnswer);
    }

    public void onDetach() {
        super.onDetach();
    }
}
