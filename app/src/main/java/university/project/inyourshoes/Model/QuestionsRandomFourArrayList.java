package university.project.inyourshoes.Model;

import java.util.ArrayList;

public class QuestionsRandomFourArrayList {

    //Has the same functionality as questions object, but with QuestionFours questions list
    public ArrayList randomQuestionFourList;

    public QuestionsRandomFourArrayList() {
        super();
    }

    public ArrayList getRandomQuestionFourList() {
        return randomQuestionFourList;
    }

    public QuestionsRandomFourArrayList setRandomQuestionFourList(ArrayList randomQuestionFourList) {
        this.randomQuestionFourList = randomQuestionFourList;
        return this;
    }

}
