package com.hackatum.bookit.ui.register;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hackatum.bookit.MainActivity;
import com.hackatum.bookit.R;
import com.hackatum.bookit.data.model.DBHandler;

public class CustomerRegistrationActivity extends AppCompatActivity {

    private RegistrationViewModel registrationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        registrationViewModel = ViewModelProviders.of(this, new RegistrationViewModelFactory())
                .get(RegistrationViewModel.class);

        final Button finishButton = findViewById(R.id.finish);
        final EditText nameEditText = findViewById(R.id.fullname);
        final EditText lastnameEditText = findViewById(R.id.lastName);
        final EditText usernameEditText = findViewById(R.id.email);
        final EditText phoneEditText = findViewById(R.id.phone);
        final EditText passwordEditText = findViewById(R.id.password);

        registrationViewModel.getRegistrationResult().observe(this, new Observer<RegistrationResult>() {
            @Override
            public void onChanged(@Nullable RegistrationResult registrationResult) {
                if (registrationResult == null) {
                    return;
                }
                if (registrationResult.getError() != null) {
                    showRegisterFailed(registrationResult.getError());
                }
                if (registrationResult.getSuccess() != null) {
                    updateUiWithUser(registrationResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                //finish();
                // TODO: go to profile. Current: go to main screen
                Intent intent = new Intent(CustomerRegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                registrationViewModel.registrationDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };

        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    registrationViewModel.register(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(), nameEditText.getText().toString(),
                            lastnameEditText.getText().toString(), phoneEditText.getText().toString(),
                            DBHandler.CUSTOMER_TABLE);
                }
                return false;
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrationViewModel.register(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(), nameEditText.getText().toString(),
                        lastnameEditText.getText().toString(), phoneEditText.getText().toString(),
                        DBHandler.CUSTOMER_TABLE);
            }
        });

    }

    private void updateUiWithUser(RegisteredUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName() + "!";
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showRegisterFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
