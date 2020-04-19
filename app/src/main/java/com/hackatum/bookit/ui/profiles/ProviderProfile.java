package com.hackatum.bookit.ui.profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hackatum.bookit.R;
import com.hackatum.bookit.data.model.LoggedInProvider;

public class ProviderProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profile);

        final TextView nameEditText = findViewById(R.id.fullname);
        final TextView profileTypeEditText = findViewById(R.id.profileType);
        final TextView businessTypeTextView = findViewById(R.id.businessType);

        LoggedInProvider user = (LoggedInProvider) getIntent().getSerializableExtra("loggedInUser");

        nameEditText.setText(user.getName() + " " + user.getLastName());
        profileTypeEditText.setText("Service provider");
        businessTypeTextView.setText(user.getBusinessType());
    }
}
