package com.example.eduvosproject.course.courseView;

public class CourseData {
    //Model used for data present in CourseViewActivity.

    public CourseClass course_data;
    public QuizClass quiz_data;
    public ResultClass result;

    public static class CourseClass {
        private int id;
        private String name;
        private String content;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getContent() {
            return content;
        }
    }

    public static class QuizClass {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class ResultClass {
        private String message;
        private Boolean error;

        public String getMessage() {
            return message;
        }

        public Boolean getError() {
            return error;
        }
    }

}
