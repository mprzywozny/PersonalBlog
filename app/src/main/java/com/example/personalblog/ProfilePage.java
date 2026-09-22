package com.example.personalblog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfilePage extends AppCompatActivity {

    private String email;
    private Button backButton;
    private FirebaseAuth auth;
    private List<Post> postList = new ArrayList<>();
    private PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_page);

        auth = FirebaseAuth.getInstance();
        email = auth.getCurrentUser().getEmail();

        TextView emailText = findViewById(R.id.emailText);
        emailText.setText(email);

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(view -> {
            startActivity(new Intent(this, Homepage.class));
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new PostAdapter(postList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getPosts();

    }

    private void getPosts() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://personalblog-7aa99-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference reference = database.getReference("posts");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Post post = postSnapshot.getValue(Post.class);
                    if (post != null) {
                        postList.add(post);
                    }
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(
                        ProfilePage.this,
                        "Posts found: " + postList.size(),
                        Toast.LENGTH_LONG
                ).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfilePage.this, "Firebase error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}