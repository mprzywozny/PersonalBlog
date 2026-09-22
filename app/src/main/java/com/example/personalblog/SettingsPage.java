package com.example.personalblog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class SettingsPage extends AppCompatActivity {

    private Button changeEmailButton, changePasswordButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings_page);

        changeEmailButton = findViewById(R.id.changeEmailButton);
        changePasswordButton = findViewById(R.id.changePasswordButton);
        backButton = findViewById(R.id.backButton);

        changeEmailButton.setOnClickListener(view -> {
            startActivity(new Intent(this, ChangeEmailPage.class));
        });

        changePasswordButton.setOnClickListener(view -> {
            startActivity(new Intent(this, ChangePasswordPage.class));
        });

        backButton.setOnClickListener(view -> {
            startActivity(new Intent(this, Homepage.class));
        });
    }
}