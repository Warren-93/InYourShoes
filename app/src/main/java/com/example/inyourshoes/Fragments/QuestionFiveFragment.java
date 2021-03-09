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

public class QuestionFiveFragment extends Fragment {

    private static final String Extra_QuestionFive = "QuestionFive";

    public static QuestionFiveFragment newInstance(String question){

        QuestionFiveFragment fragmentFive = new QuestionFiveFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Extra_QuestionFive", question);
        fragmentFive.setArguments(bundle);
        return fragmentFive;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.question_five, container, false);

        String question_Five = getArguments().getString("Extra_QuestionFive");
        TextView questionFive = (TextView) rootView.findViewById(R.id.questionFiveText);
        questionFive.setText(question_Five);


        return rootView;

    }


    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    public interface OnFragmentFiveListener {
        void onQuestionFiveAnswerUpdated(String questionOneAnswer);
    }

    public void onDetach() {
        super.onDetach();
    }


}
