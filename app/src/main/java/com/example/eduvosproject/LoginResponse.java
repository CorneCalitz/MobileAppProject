package com.example.eduvosproject;

public class LoginResponse {
    // Model for the response data retrieved from the server on successful login

    public UserClass user;
    public ResultClass result;
    public ProfileClass profile;


    public class ProfileClass {
        // User profile data

        String profile_id;
        Integer tests_taken, tests_passed;

        public Integer getTests_passed() {
            return tests_passed;
        }

        public void setTests_passed(Integer tests_passed) {
            this.tests_passed = tests_passed;
        }

        public Integer getTests_taken() {
            return tests_taken;
        }

        public void setTests_taken(Integer tests_taken) {
            this.tests_taken = tests_taken;
        }

        public String getProfile_id() {
            return profile_id;
        }

        public void setProfile_id(String profile_id) {
            this.profile_id = profile_id;
        }
    }

    public class UserClass {
        // User data.

        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class ResultClass {
        // Result data

        String message;
        Boolean error;

        public Boolean getError() {
            return error;
        }

        public void setError(Boolean error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
