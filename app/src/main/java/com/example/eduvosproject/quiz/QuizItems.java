package com.example.eduvosproject.quiz;

public class QuizItems {
    // Model used to store quiz recycleView data.
    int id;
    String name, quizCourseId;

    public QuizItems(int id, String name, String quizCourseId) {
        this.id = id;
        this.name = name;
        this.quizCourseId = quizCourseId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuizCourseId() {return quizCourseId;}

    public void setQuizCourseId(String quizCourseID) {this.quizCourseId = quizCourseID;}
}
