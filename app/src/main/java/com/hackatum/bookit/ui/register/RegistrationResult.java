package com.hackatum.bookit.ui.register;

import androidx.annotation.Nullable;

public class RegistrationResult {
    @Nullable
    private RegisteredUserView success;
    @Nullable
    private Integer error;

    RegistrationResult(@Nullable Integer error) {
        this.error = error;
    }

    RegistrationResult(@Nullable RegisteredUserView success) {
        this.success = success;
    }

    @Nullable
    RegisteredUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
