package com.hackatum.bookit.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class LoggedInProvider extends LoggedInUser implements Serializable {

    private String businessName;
    private String businessType;

    public LoggedInProvider(){}

    public LoggedInProvider(String userName, String password, String name, String lastName, String phoneNumber, String businessName, String businessType) {
        super(userName, password, name, lastName, phoneNumber);
        this.businessType = businessType;
        this.businessName = businessName;
    }

    protected LoggedInProvider(Parcel in) {
        businessName = in.readString();
        businessType = in.readString();
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
