package university.project.inyourshoes.Model;

import java.util.ArrayList;

public class QuestionArrayList extends ArrayList<Questions> {

    //Extension of Arraylist extended by Question object
    public ArrayList questionList;

    public QuestionArrayList() {
        super();
    }


    public ArrayList getStaticQuestionList() {
        return questionList;
    }

    public QuestionArrayList setStaticQuestionList(ArrayList staticQuestionList) {
        this.questionList = staticQuestionList;
        return this;
    }









}
