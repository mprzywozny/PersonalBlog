package com.example.personalblog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

public class CreatePostPage extends AppCompatActivity {

    private EditText postInput;
    private Button confirmButton, cancelButton;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_post_page);

        postInput = findViewById(R.id.postInput);
        confirmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);

        auth = FirebaseAuth.getInstance();

        confirmButton.setOnClickListener(view -> {
            String content = postInput.getText().toString().trim();
            if(!content.isEmpty()) {
                writePost(auth.getCurrentUser().getEmail(), content);
                Toast.makeText(this, "Posted!", Toast.LENGTH_LONG).show();
                postInput.setText("");
            } else{
                Toast.makeText(this, "Your post cannot be empty", Toast.LENGTH_LONG).show();
            }
        });

        cancelButton.setOnClickListener(view -> {
            startActivity(new Intent(CreatePostPage.this, Homepage.class));
        });


    }

    private void writePost(String email, String content) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://personalblog-7aa99-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference reference = database.getReference("posts").push();
        HashMap<String, Object> post = new HashMap<>();
        post.put("email", email);
        post.put("content", content);
        post.put("time", ServerValue.TIMESTAMP);
        reference.setValue(post).addOnSuccessListener(unused -> {
            Toast.makeText(this, "Posted!", Toast.LENGTH_LONG).show();postInput.setText("");
        }).addOnFailureListener(e -> {Toast.makeText(this, "Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }

}