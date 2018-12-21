package com.example.stuar.tutorialuno;

public class Doctor {

    private int id;
    private String fullName;

    private String password;
    private String email;

    public Doctor(int id, String fullName, String password, String email) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    public Doctor(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}





