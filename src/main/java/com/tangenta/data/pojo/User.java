package com.tangenta.data.pojo;

public class User {
    private Long studentId;
    private String username;
    private String password;
    private String email;
    private String creationDate;

    public User() {}

    public User(Long studentId, String username, String password, String email, String date) {
        this.studentId = studentId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.creationDate = date;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public Long getStudentId() {
        return studentId;
    }
}
