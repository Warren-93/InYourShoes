package university.project.inyourshoes.Fragments;

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

import university.project.inyourshoes.Interfaces.IFragment;
import university.project.inyourshoes.Views.R;

public class QuestionFiveFragment extends Fragment implements IFragment {

    //UI Layout for the fragment for question three in the viewpager for FiveQs
    private static final String Extra_QuestionFive = "QuestionFive";
    EditText questionFiveAnswer;
    TextView questionFive;

    //Collecting data on fragment view from the bundle to send the corresponding question two
    // in the array list questions
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
        //sets data to display in the fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.question_five, container, false);
        String question_Five = getArguments().getString("Extra_QuestionFive");
        questionFive = rootView.findViewById(R.id.questionFive);
        questionFiveAnswer = rootView.findViewById(R.id.questionFiveAnswer);
        questionFive.setText(question_Five);


        return rootView;

    }

    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    //Uses interface from main thread on click of submit to collect the answer from the fragment
    @Override
    public String onQuestionAnswer() {
        return questionFiveAnswer.getText().toString();
    }

    public void onDetach() {
        super.onDetach();
    }



}
