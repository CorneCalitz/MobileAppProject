package com.example.eduvosproject;

public class LoginResponse {

    public String status;
    public String message;
    public UserClass data;

    public UserClass user;
    public ResultClass result;
    public ProfileClass profile;

    public static class UserClass {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ResultClass {
        private String message;
        private Boolean error;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Boolean getError() {
            return error;
        }

        public void setError(Boolean error) {
            this.error = error;
        }
    }


    public static class ProfileClass {
        private int tests_taken;
        private int tests_passed;
        private int profile_id;

        // Getters and Setters
        public int getTests_taken() {
            return tests_taken;
        }

        public void setTests_taken(int tests_taken) {
            this.tests_taken = tests_taken;
        }

        public int getTests_passed() {
            return tests_passed;
        }

        public void setTests_passed(int tests_passed) {
            this.tests_passed = tests_passed;
        }

        public int getProfile_id() {
            return profile_id;
        }

        public void setProfile_id(int profile_id) {
            this.profile_id = profile_id;
        }
    }

}
