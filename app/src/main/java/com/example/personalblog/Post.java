package com.example.personalblog;

public class Post {

    private String email;
    private String content;
    private long time;

    public Post(){

    }
    public Post(String email, String content, long time) {
        this.email = email;
        this.content = content;
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public String getContent() {
        return content;
    }

    public long getTime() {
        return time;
    }
}
