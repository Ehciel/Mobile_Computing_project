package com.chili.shockback;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FeedbackFormActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);

        // Back "X" button functionality
        ImageView closeButton = findViewById(R.id.btnClose);
        closeButton.setOnClickListener(view -> finish()); // Close the activity when "X" is clicked

        dbHelper = new DBHelper(this);

        // Mapping the form fields
        EditText etSatisfaction = findViewById(R.id.etSatisfaction);
        EditText etResponsiveness = findViewById(R.id.etResponsiveness);
        EditText etSuggestions = findViewById(R.id.etSuggestions);
        EditText etComments = findViewById(R.id.etComments);

        // RadioGroups for Reliability and Customer Service
        RadioGroup rgReliability = findViewById(R.id.rgReliability);
        RadioGroup rgCustomerService = findViewById(R.id.rgCustomerService);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {
            // Collecting data from text fields
            int satisfaction = Integer.parseInt(etSatisfaction.getText().toString());
            String responsiveness = etResponsiveness.getText().toString();
            String suggestions = etSuggestions.getText().toString();
            String comments = etComments.getText().toString();

            // Collecting data from RadioGroup for Reliability of Service
            String reliability = "";
            int selectedReliabilityId = rgReliability.getCheckedRadioButtonId();
            if (selectedReliabilityId != -1) {
                RadioButton selectedReliability = findViewById(selectedReliabilityId);
                reliability = selectedReliability.getText().toString();
            }

            // Collecting data from RadioGroup for Customer Service Experience
            String customerService = "";
            int selectedCustomerServiceId = rgCustomerService.getCheckedRadioButtonId();
            if (selectedCustomerServiceId != -1) {
                RadioButton selectedCustomerService = findViewById(selectedCustomerServiceId);
                customerService = selectedCustomerService.getText().toString();
            }

            // Insert feedback into the database
            boolean isInserted = dbHelper.insertFeedback(satisfaction, reliability, responsiveness, customerService, "", "", suggestions, comments);
            if (isInserted) {
                Toast.makeText(FeedbackFormActivity.this, "Feedback submitted!", Toast.LENGTH_SHORT).show();
                finish(); // Close the form
            } else {
                Toast.makeText(FeedbackFormActivity.this, "Error submitting feedback!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
