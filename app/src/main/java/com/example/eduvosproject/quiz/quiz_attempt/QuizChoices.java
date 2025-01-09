package com.example.eduvosproject.quiz.quiz_attempt;

public class QuizChoices {
    int id;
    String choice;

    public QuizChoices(int id, String choice) {
        this.id = id;
        this.choice = choice;
    }

    public int getId() {
        return id;
    }

    public String getChoice() {
        return choice;
    }
}
