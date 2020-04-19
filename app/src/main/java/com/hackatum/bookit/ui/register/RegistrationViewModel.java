package com.hackatum.bookit.ui.register;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hackatum.bookit.R;
import com.hackatum.bookit.data.Result;
import com.hackatum.bookit.data.model.LoggedInUser;

public class RegistrationViewModel extends ViewModel {

    private MutableLiveData<RegistrationFormState> registrationFormState = new MutableLiveData<>();
    private MutableLiveData<RegistrationResult> registrationResult = new MutableLiveData<>();
    private RegistrationRepository registrationRepository;

    RegistrationViewModel(RegistrationRepository registrationRepository){
        this.registrationRepository = registrationRepository;
    }

    LiveData<RegistrationFormState> getRegistrationFormState() {
        return registrationFormState;
    }

    LiveData<RegistrationResult> getRegistrationResult() {
        return registrationResult;
    }

    public void register(String username, String password, String name, String lastName, String phoneNumber, String table) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = registrationRepository.register(username, password, name, lastName, phoneNumber, table);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            registrationResult.setValue(new RegistrationResult(new RegisteredUserView(data.getName())));
        } else {
            registrationResult.setValue(new RegistrationResult(R.string.login_failed));
        }
    }

    public void registrationDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            registrationFormState.setValue(new RegistrationFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            registrationFormState.setValue(new RegistrationFormState(null, R.string.invalid_password));
        } else {
            registrationFormState.setValue(new RegistrationFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
