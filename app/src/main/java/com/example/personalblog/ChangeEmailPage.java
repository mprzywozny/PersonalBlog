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

public class ChangeEmailPage extends AppCompatActivity {

    private EditText currentEmailText;
    private EditText newEmailText;
    private Button confirmButton;
    private Button cancelButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_email_page);

        auth = FirebaseAuth.getInstance();

        currentEmailText = findViewById(R.id.currentEmailInput);
        newEmailText = findViewById(R.id.newEmailInput);
        confirmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);

        confirmButton.setOnClickListener(view -> {
            String currentEmail = currentEmailText.getText().toString().trim();
            String newEmail = newEmailText.getText().toString().trim();

            if (currentEmail.isEmpty() || newEmail.isEmpty()) {
                Toast.makeText(this, "Please enter your current and new email", Toast.LENGTH_LONG).show();
                return;
            }

            if (!currentEmail.equals(auth.getCurrentUser().getEmail())) {
                Toast.makeText(this, "Current email is incorrect", Toast.LENGTH_LONG).show();
                return;
            }

            auth.getCurrentUser().updateEmail(newEmail).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(ChangeEmailPage.this, "Email updated successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ChangeEmailPage.this, "Email update failed", Toast.LENGTH_LONG).show();
                }
            });
        });

        cancelButton.setOnClickListener(view -> {
            startActivity(new Intent(this, SettingsPage.class));
        });



    }
}