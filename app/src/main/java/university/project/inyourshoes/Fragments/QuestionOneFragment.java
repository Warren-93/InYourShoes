package university.project.inyourshoes.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import university.project.inyourshoes.Interfaces.IFragment;
import university.project.inyourshoes.Views.R;




public class QuestionOneFragment extends Fragment implements IFragment {
    //UI Layout for the fragment for question one in the viewpager for FiveQs
    private static final String Extra_QuestionOne = "QuestionOne";
    EditText questionOneAnswer;
    TextView questionOne;


    //Collecting data on fragment view from the bundle to send the corresponding question one
    // in the array list questions
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
        //sets data to display in the fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.question_one, parent, false);
        String question_one = getArguments().getString("Extra_QuestionOne");
        questionOne = rootView.findViewById(R.id.questionOne);
        questionOneAnswer = rootView.findViewById(R.id.questionOneAnswer);
        questionOne.setText(question_one);
        return rootView;
    }

    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }


    //Uses interface from main thread on click of submit to collect the answer from the fragment
    @Override
    public String onQuestionAnswer() {
        return questionOneAnswer.getText().toString();
    }
    public void onDetach() {
        super.onDetach();
    }


}
