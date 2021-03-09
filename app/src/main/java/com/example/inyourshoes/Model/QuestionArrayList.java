package com.example.inyourshoes.Model;

import java.util.ArrayList;

public class QuestionArrayList extends ArrayList<Questions> {


    public ArrayList staticQuestionList;

    public QuestionArrayList() {
        super();
    }


    public ArrayList getStaticQuestionList() {
        return staticQuestionList;
    }

    public QuestionArrayList setStaticQuestionList(ArrayList staticQuestionList) {
        this.staticQuestionList = staticQuestionList;
        return this;
    }









}
