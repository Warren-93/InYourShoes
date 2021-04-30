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

public class QuestionFourFragment extends Fragment implements IFragment {

    //UI Layout for the fragment for question three in the viewpager for FiveQs
    private static final String Extra_QuestionFour = "QuestionFour";
    TextView questionFour;
    EditText questionFourAnswer;

    //Collecting data on fragment view from the bundle to send the corresponding question four
    // in the array list questions
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
        //sets data to display in the fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.question_four, container, false);

        String question_Four = getArguments().getString("Extra_QuestionFour");
        questionFour = (TextView) rootView.findViewById(R.id.questionFour);
        questionFourAnswer = rootView.findViewById(R.id.questionFourAnswer);
        questionFour.setText(question_Four);
        return rootView;
    }

    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    //Uses interface from main thread on click of submit to collect the answer from the fragment
    public String onQuestionAnswer() {
        return questionFourAnswer.getText().toString();
    }

    public void onDetach() {
        super.onDetach();
    }

}
