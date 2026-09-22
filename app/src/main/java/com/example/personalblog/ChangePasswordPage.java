package com.example.personalblog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class ChangePasswordPage extends AppCompatActivity {

    private EditText currentPasswordText;
    private EditText newPasswordText;
    private EditText confirmPasswordText;
    private Button confirmButton;
    private Button cancelButton;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password_page);

        currentPasswordText = findViewById(R.id.currentPasswordInput);
        newPasswordText = findViewById(R.id.newPasswordInput);
        confirmPasswordText = findViewById(R.id.confirmPasswordInput);
        confirmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);

        auth = FirebaseAuth.getInstance();

        confirmButton.setOnClickListener(view -> {
            String currentPassword = currentPasswordText.getText().toString().trim();
            String newPassword = newPasswordText.getText().toString().trim();
            String confirmPassword = confirmPasswordText.getText().toString().trim();

            if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please enter your current and new password", Toast.LENGTH_LONG).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
                return;
            }

            if (newPassword.length() < 6) {
                Toast.makeText(this, "Your password is too short", Toast.LENGTH_LONG).show();
                return;
            }

            auth.getCurrentUser().updatePassword(newPassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(ChangePasswordPage.this, "Password updated successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ChangePasswordPage.this, "Password update failed", Toast.LENGTH_LONG).show();
                }
            });

        });

        cancelButton.setOnClickListener(view -> {
            startActivity(new Intent(this, SettingsPage.class));
        });
    }
}