package com.example.personalblog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity {

    private Button createPostButton, viewProfileButton, settingsButton, signOutButton;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);

        createPostButton = findViewById(R.id.createPostButton);
        viewProfileButton = findViewById(R.id.viewProfileButton);
        settingsButton = findViewById(R.id.settingsButton);
        signOutButton = findViewById(R.id.signOutButton);

        auth = FirebaseAuth.getInstance();

        createPostButton.setOnClickListener(view -> {
            startActivity(new Intent(this, CreatePostPage.class));
        });

        viewProfileButton.setOnClickListener(view -> {
            startActivity(new Intent(this, ProfilePage.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(this, SettingsPage.class));
        });

        signOutButton.setOnClickListener(view -> {
            auth.signOut();
            startActivity(new Intent(this, LoginPage.class));
        });

    }
}