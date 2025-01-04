package com.example.eduvosproject;

public class LoginRequest {
    private String name;
    private String password;

    public LoginRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
