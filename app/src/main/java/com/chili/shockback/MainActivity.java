package com.chili.shockback;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle FloatingActionButton click
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            // Open Feedback Form Activity
            Intent intent = new Intent(MainActivity.this, FeedbackFormActivity.class);
            startActivity(intent);
        });

        // Handle BottomNavigationView item selection using if-else
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                // Stay on MainActivity (Home)
                Toast.makeText(MainActivity.this, "Home selected", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.navigation_profile) {
                // Navigate to ContactUsActivity
                Intent intent = new Intent(MainActivity.this, com.chili.shockback.ContactUsActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.navigation_feedback) {
                // Navigate to FeedbackListActivity
                Intent intent = new Intent(MainActivity.this, FeedbackListActivity.class);
                startActivity(intent);
                return true;
            }

            return false;
        });
    }
}
