package com.totvs.UserRegister.domain;

public class PhoneNumber{

    private String userID;
    private String number;

    public PhoneNumber(String userID, String number) {
        this.userID = userID;
        this.number = number;
    }

    public PhoneNumber(String number) {
        this.number = number;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
