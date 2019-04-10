package com.techpakka.pscquestionbank.model;

public class Data {
    String question,answer,category,category_id;

    public Data(){

    }

    public Data(String category,String category_id) {
        this.category_id = category_id;
        this.category = category;
    }

    public Data(String question, String answer, String category) {
        this.question = question;
        this.answer = answer;
        this.category = category;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
