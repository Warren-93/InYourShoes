package university.project.inyourshoes.Model;

import java.util.ArrayList;

public class QuestionsRandomFiveArrayList {

    //Has the same functionality as main questions object, but with QuestionFive questions list
    public ArrayList randomQuestionFiveList;

    public QuestionsRandomFiveArrayList() {
        super();
    }


    public ArrayList getRandomQuestionFiveList()
    {
        return randomQuestionFiveList;
    }

    public QuestionsRandomFiveArrayList setRandomQuestionFiveList(ArrayList randomQuestionFiveList) {
        this.randomQuestionFiveList = randomQuestionFiveList;
        return this;
    }

}
