package com.hackatum.bookit.data.model;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser implements Serializable {

    protected int id;
    /** Same as email */
    protected String userName;
    protected String password;
    protected String name;
    protected String lastName;
    protected String phoneNumber;

    public LoggedInUser(){}

    public LoggedInUser(String userName, String password, String name, String lastName, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    protected LoggedInUser(Parcel in) {
        id = in.readInt();
        userName = in.readString();
        password = in.readString();
        name = in.readString();
        lastName = in.readString();
        phoneNumber = in.readString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
