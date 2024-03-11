package com.totvs.UserRegister.domain;

/**
 * The type Phone number.
 */
public class PhoneNumber {

    private String userID;
    private String number;

    /**
     * Instantiates a new Phone number.
     *
     * @param userID the user id
     * @param number the number
     */
    public PhoneNumber(String userID, String number) {
        this.userID = userID;
        this.number = number;
    }

    /**
     * Instantiates a new Phone number.
     *
     * @param number the number
     */
    public PhoneNumber(String number) {
        this.number = number;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(String number) {
        this.number = number;
    }
}
