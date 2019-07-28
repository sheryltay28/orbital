package com.orbital.saveme.model;

public class User {
    public String userName;
    public String userNumber;

    public User(String userName, String userNumber) {
        this.userName = userName;
        this.userNumber = userNumber;
    }

    public User() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }
}
