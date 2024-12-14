package com.chili.shockback;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FeedbackListActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private RecyclerView feedbackRecyclerView;
    private FeedbackAdapter feedbackAdapter;
    private ArrayList<String> feedbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);

        dbHelper = new DBHelper(this);
        feedbackRecyclerView = findViewById(R.id.feedbackRecyclerView);
        feedbackRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the feedback list
        feedbacks = new ArrayList<>();

        // Load feedbacks and handle possible errors
        loadFeedbacks();

        // Handle BottomNavigationView item selection
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                // Navigate to MainActivity
                finish(); // Close current activity and return to MainActivity

            } else if (itemId == R.id.navigation_feedback) {
                // Already on FeedbackListActivity, show a message
                Toast.makeText(FeedbackListActivity.this, "Already on Feedback", Toast.LENGTH_SHORT).show();
                return true;
            }

            return false;
        });
    }

    private void loadFeedbacks() {
        try {
            Cursor cursor = dbHelper.getAllFeedbacks();
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String feedback = "Satisfaction: " + cursor.getString(cursor.getColumnIndex("satisfaction"))
                            + "\nReliability: " + cursor.getString(cursor.getColumnIndex("reliability"))
                            + "\nResponsiveness: " + cursor.getString(cursor.getColumnIndex("responsiveness"))
                            + "\nCustomer Service: " + cursor.getString(cursor.getColumnIndex("customer_service"))
                            + "\nBilling Process: " + cursor.getString(cursor.getColumnIndex("billing_process"))
                            + "\nCommunication: " + cursor.getString(cursor.getColumnIndex("communication"))
                            + "\nSuggestions: " + cursor.getString(cursor.getColumnIndex("suggestions"))
                            + "\nComments: " + cursor.getString(cursor.getColumnIndex("comments"));
                    feedbacks.add(feedback);
                } while (cursor.moveToNext());

                cursor.close();
            } else {
                Toast.makeText(this, "No feedbacks found", Toast.LENGTH_SHORT).show();
            }

            // Set up the adapter
            feedbackAdapter = new FeedbackAdapter(feedbacks, this);
            feedbackRecyclerView.setAdapter(feedbackAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error retrieving feedbacks", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
