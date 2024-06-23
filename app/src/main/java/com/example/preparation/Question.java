package com.example.preparation;

import java.util.List;

public class Question {
    private String text;
    private List<String> options;
    private int correctAnswer;
    private String explanation;

    public Question(String text, List<String> options, int correctAnswer, String explanation) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }
}
