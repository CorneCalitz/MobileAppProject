package com.example.eduvosproject.quiz.quiz_attempt;

public class QuizQuestions {
    //Model used to carry quiz questions.
    int id, answer;
    String question, context;

    public QuizQuestions(int id, int answer, String question, String context) {
        this.id = id;
        this.answer = answer;
        this.question = question;
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public int getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getContext() {
        return context;
    }
}
