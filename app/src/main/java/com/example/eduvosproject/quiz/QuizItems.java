package com.example.eduvosproject.quiz;

public class QuizItems {
    // Model used to store quiz recycleView data.

    String id, name, score;

    public QuizItems(String id, String name, String score) {
        this.id = id;
        this.name = name;
        this.score = score;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
