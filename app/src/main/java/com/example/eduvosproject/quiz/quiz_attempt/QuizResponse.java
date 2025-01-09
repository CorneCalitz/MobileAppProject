package com.example.eduvosproject.quiz.quiz_attempt;

public class QuizResponse {
    //Model used to store quiz response data.
    int profileId, questionId, questionResponse;

    public QuizResponse(int profileId, int questionId, int questionResponse) {
        this.profileId = profileId;
        this.questionId = questionId;
        this.questionResponse = questionResponse;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getQuestionResponse() {
        return questionResponse;
    }

    public void setQuestionResponse(int questionResponse) {
        this.questionResponse = questionResponse;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
