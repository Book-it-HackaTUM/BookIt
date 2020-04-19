package com.hackatum.bookit.ui.register;

public class RegisteredUserView {
    private String displayName;

    RegisteredUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}
