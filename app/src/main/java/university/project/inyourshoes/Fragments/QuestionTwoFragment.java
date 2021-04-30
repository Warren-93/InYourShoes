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

public class QuestionTwoFragment extends Fragment implements IFragment {

    //UI Layout for the fragment for question two in the viewpager for FiveQs
    private static final String Extra_QuestionTwo = "QuestionTwo";

    TextView questionTwo;
    EditText questionTwoAnswer;


    //Collecting data on fragment view from the bundle to send the corresponding question two
    // in the array list questions
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
        //sets data to display in the fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.question_two, container, false);
        String question_Two = getArguments().getString("Extra_QuestionTwo");

        questionTwo = (TextView) rootView.findViewById(R.id.questionTwo);
        questionTwoAnswer = rootView.findViewById(R.id.questionTwoAnswer);
        questionTwo.setText(question_Two);

        return rootView;
    }

    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    //Uses interface from main thread on click of submit to collect the answer from the fragment
    public String onQuestionAnswer() {
        return questionTwoAnswer.getText().toString();
    }

    public void onDetach() {
        super.onDetach();
    }
}