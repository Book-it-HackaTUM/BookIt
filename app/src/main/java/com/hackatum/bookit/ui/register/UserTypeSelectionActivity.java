package com.hackatum.bookit.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hackatum.bookit.R;

public class UserTypeSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);

        final Button customerButton = findViewById(R.id.customer);
        final Button providerButton = findViewById(R.id.provider);

        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserTypeSelectionActivity.this, CustomerRegistrationActivity.class);
                startActivity(intent);
            }
        });

        providerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserTypeSelectionActivity.this, ProviderRegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
}
