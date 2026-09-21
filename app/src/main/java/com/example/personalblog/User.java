package com.example.personalblog;

public class User {
    private String username;
    private String email;
    //private String password;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
       // this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
