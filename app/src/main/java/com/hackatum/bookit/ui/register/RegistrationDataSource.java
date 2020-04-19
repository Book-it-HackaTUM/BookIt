package com.hackatum.bookit.ui.register;

import com.hackatum.bookit.data.Result;
import com.hackatum.bookit.data.model.DBHandler;
import com.hackatum.bookit.data.model.LoggedInUser;

import java.io.IOException;

class RegistrationDataSource {

    public Result<LoggedInUser> register(String userName, String password, String name, String lastName, String phoneNumber, String table) {

        DBHandler db = DBHandler.getInstance();
        LoggedInUser user;
        user = db.findHandler(userName, DBHandler.CUSTOMER_TABLE);

        if(user == null){
            user = new LoggedInUser(userName, password, name, lastName, phoneNumber);
            db.addRegistry(user, table);
            return new Result.Success<>(user);
        } else {
            return new Result.Error(new IOException("Registration error"));
        }
    }
}
