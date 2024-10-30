package com.example.eduvosproject.course;



public class CourseItemModel {
    // A simple model used to store course view data

    String name;
    String description;
    int image;

    public CourseItemModel(int image, String description, String name) {
        this.image = image;
        this.description = description;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}

