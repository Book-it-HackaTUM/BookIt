package com.hackatum.bookit.ui.profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.hackatum.bookit.R;
import com.hackatum.bookit.data.model.LoggedInCustomer;

public class CustomerProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        final TextView nameEditText = findViewById(R.id.fullname);
        final TextView profileTypeEditText = findViewById(R.id.profileType);

        LoggedInCustomer user = (LoggedInCustomer) getIntent().getParcelableExtra("loggedInUser");

        nameEditText.setText(user.getName() + " " + user.getLastName());
        profileTypeEditText.setText("Customer");
    }
}
