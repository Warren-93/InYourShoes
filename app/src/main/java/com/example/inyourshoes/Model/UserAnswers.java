package com.example.inyourshoes.Model;

public class UserAnswers {

    String userId;
    String question, questionTwo, questionThree, questionFour, questionFive;
    String questionAnswer;

    public UserAnswers() {
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

}