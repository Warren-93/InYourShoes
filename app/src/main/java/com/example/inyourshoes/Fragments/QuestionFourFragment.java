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

public class QuestionFourFragment extends Fragment {

    private static final String Extra_QuestionFour = "QuestionFour";

    public static QuestionFourFragment newInstance(String question){

        QuestionFourFragment fragmentFour = new QuestionFourFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Extra_QuestionFour", question);
        fragmentFour.setArguments(bundle);
        return fragmentFour;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.question_four, container, false);


        String question_Four = getArguments().getString("Extra_QuestionFour");
        TextView questionFour = (TextView) rootView.findViewById(R.id.questionFourText);
        questionFour.setText(question_Four);

        return rootView;

    }

    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    public interface OnFragmentFourListener {
        void onQuestionFourAnswerUpdated(String questionOneAnswer);
    }

    public void onDetach() {
        super.onDetach();
    }

}
