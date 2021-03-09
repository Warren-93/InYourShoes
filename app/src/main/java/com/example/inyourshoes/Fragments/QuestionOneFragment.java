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
import com.example.inyourshoes.Interfaces.IFragmentOne;
import com.example.inyourshoes.Views.R;




public class QuestionOneFragment extends Fragment implements IFragmentOne {

    private static final String Extra_QuestionOne = "QuestionOne";
    EditText questionOneAnswer;
    TextView questionOne;


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
        questionOneAnswer = rootView.findViewById(R.id.questionOneAnswer);
        questionOne.setText(question_one);

        return rootView;
    }

    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }

    public String onQuestionOneAnswer() {
        return questionOneAnswer.getText().toString();
    }
}
