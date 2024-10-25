package com.example.eduvosproject;

public class CourseItemModel {

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

