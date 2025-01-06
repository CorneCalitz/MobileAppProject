package com.example.eduvosproject.course;

import java.util.List;

public class CourseItems {
    // A simple model used to store course recycle view data

   String name, description;
   int id;

    public CourseItems(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

