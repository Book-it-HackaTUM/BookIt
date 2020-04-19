package com.hackatum.bookit.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class LoggedInCustomer extends LoggedInUser implements Serializable {

    public LoggedInCustomer(){}

    public LoggedInCustomer(String userName, String password, String name, String lastName, String phoneNumber) {
        super(userName, password, name, lastName, phoneNumber);
    }

}
