package com.hackatum.bookit.ui.register;

import android.view.View;

import com.hackatum.bookit.data.Result;
import com.hackatum.bookit.data.model.DBHandler;
import com.hackatum.bookit.data.model.LoggedInUser;

/**
 * Class that requests remote data source to add new registries.
 */
class RegistrationRepository {

    private static volatile RegistrationRepository instance;

    private RegistrationDataSource dataSource;

    private LoggedInUser user = null;

    // private constructor : singleton access
    private RegistrationRepository(RegistrationDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static RegistrationRepository getInstance(RegistrationDataSource dataSource) {
        if (instance == null) {
            instance = new RegistrationRepository(dataSource);
        }
        return instance;
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInUser> register(String username, String password, String name, String lastName, String phoneNumber, String table) {
        // handle register
        Result<LoggedInUser> result = dataSource.register(username, password, name, lastName, phoneNumber, table);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }
}
