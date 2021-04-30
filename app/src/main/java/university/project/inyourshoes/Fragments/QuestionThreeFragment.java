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

public class QuestionThreeFragment extends Fragment implements IFragment {

    //UI Layout for the fragment for question three in the viewpager for FiveQs
    private static final String Extra_QuestionThree = "QuestionThree";
    EditText questionThreeAnswer;
    TextView questionThree;

    //Collecting data on fragment view from the bundle to send the corresponding question two
    // in the array list questions
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
        //sets data to display in the fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.question_three, container,
                false);

        String question_Three = getArguments().getString("Extra_QuestionThree");
        questionThree = rootView.findViewById(R.id.questionThree);
        questionThreeAnswer = rootView.findViewById(R.id.questionThreeAnswer);
        questionThree.setText(question_Three);
        return rootView;

    }

    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    //Uses interface from main thread on click of submit to collect the answer from the fragment
    public String onQuestionAnswer() {
        return questionThreeAnswer.getText().toString();
    }

    public void onDetach() {
        super.onDetach();
    }
}
