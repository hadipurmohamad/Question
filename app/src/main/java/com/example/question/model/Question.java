package com.example.question.model;

public class Question {
    private String question;
    private boolean resualt;

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", resualt=" + resualt +
                '}';
    }

    public Question(String question, boolean resualt) {
        this.question = question;
        this.resualt = resualt;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isResualt() {
        return resualt;
    }

    public void setResualt(boolean resualt) {
        this.resualt = resualt;
    }
}
