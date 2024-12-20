package com.example.eduvosproject.course;

import java.util.List;

public class CourseItems {
    // A simple model used to store course view data

   String id, name, description, content;

    public CourseItems(String id, String name, String description, String content) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.content = content;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}
}

