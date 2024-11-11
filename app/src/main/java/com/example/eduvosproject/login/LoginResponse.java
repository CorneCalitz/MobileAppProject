package com.example.eduvosproject.login;

public class LoginResponse {
    // Model for the response data retrieved from the server on successful login

    public UserClass user;
    public ResultClass result;

    public class UserClass {
        // User data.

        String id, name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class ResultClass {
        // Result data

        String error, message;

        public String getError() {
            return error;
        }

        public void setError(String error) {
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
