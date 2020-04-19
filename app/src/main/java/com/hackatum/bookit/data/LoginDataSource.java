package com.hackatum.bookit.data;

import android.content.Context;
import android.content.Intent;

import com.hackatum.bookit.data.model.DBHandler;
import com.hackatum.bookit.data.model.LoggedInUser;
import com.hackatum.bookit.ui.login.LoginActivity;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String userName, String password) {

        DBHandler db = DBHandler.getInstance();

        LoggedInUser customerUser = db.findHandler(userName, DBHandler.CUSTOMER_TABLE);
        LoggedInUser providerUser = db.findHandler(userName, DBHandler.PROVIDER_TABLE);

        if(customerUser != null) {
            return new Result.Success<>(customerUser);
        } else if (providerUser != null){
            return new Result.Success<>(providerUser);
        } else {
            logout();
            return new Result.Error(new IOException("Error logging in"));
        }
    }

    public void logout() {
        System.err.println("User name or password not found");

    }
}
