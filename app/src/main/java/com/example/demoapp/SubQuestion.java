package com.example.demoapp;

import java.util.ArrayList;

public class SubQuestion {
    private String category;
    private String question;
    private String correct_answer;
    private ArrayList<String> incorrect_answers;

    // Constructor, getters, and setters

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

    public String getCorrectAnswer() {
        return correct_answer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correct_answer = correctAnswer;
    }

    public ArrayList<String> getIncorrectAnswers() {
        return incorrect_answers;
    }

    public void setIncorrectAnswers(ArrayList<String> incorrectAnswers) {
        this.incorrect_answers = incorrectAnswers;
    }
}