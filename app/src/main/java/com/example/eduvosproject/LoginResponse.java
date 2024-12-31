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
    // User class to hold the user data
    public static class User {
        private int id;
        private String username;
        private String password;
        private String createdAt;

        // Getter and Setter methods
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    // Make ProfileClass static
    public static class ProfileClass {
        private int tests_taken;
        private int tests_passed;
        private String profile_id;

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

        public String getProfile_id() {
            return profile_id;
        }

        public void setProfile_id(String profile_id) {
            this.profile_id = profile_id;
        }
    }

}
