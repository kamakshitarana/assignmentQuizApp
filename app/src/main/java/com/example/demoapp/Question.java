package com.example.demoapp;

import java.util.ArrayList;
import java.util.Collections;

public class Question {
    private String category;
    private String question;
    private String correct_answer;
    private String user1SelectedPos = "";
    private String user2SelectedPos = "";
    private ArrayList<String> allQuestions = new ArrayList<>();

    public ArrayList<String> getAllQuestions() {
        return allQuestions;
    }

    public void setAllQuestions(ArrayList<String> allQuestions) {
        this.allQuestions = allQuestions;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getUser1SelectedPos() {
        return user1SelectedPos;
    }

    public void setUser1SelectedPos(String user1SelectedPos) {
        this.user1SelectedPos = user1SelectedPos;
    }

    public String getUser2SelectedPos() {
        return user2SelectedPos;
    }

    public void setUser2SelectedPos(String user2SelectedPos) {
        this.user2SelectedPos = user2SelectedPos;
    }

    public ArrayList<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(ArrayList<String> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }

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