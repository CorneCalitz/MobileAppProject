package com.example.eduvosproject.quiz.quiz_attempt;

public class QuizData {
    // Model for data used in quiz functions and for showing quiz info.

    public CourseClass course_data;
    public QuizClass quiz_data;
    public ResultClass result;
    public ScoreClass score;

    public static class CourseClass {
        private String name;

        public String getName() {
            return name;
        }
    }

    public static class QuizClass {
        private int id, quizCourseId;
        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getQuizCourseId(){
            return quizCourseId;
        }
    }

    public static class ScoreClass {
        private int questionAmount, score, profile;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getQuestionAmount() {
            return questionAmount;
        }

        public int getProfile() {
            return profile;
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
