package com.chili.shockback;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.chili.shockback.MainActivity;
import com.chili.shockback.R;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us); // Linking XML layout

        // Phone Icon - Opens Dialer with Predefined Number
        ImageView phoneIcon = findViewById(R.id.iv_contact_phone);
        phoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:2113031")); // Replace with your number
                startActivity(callIntent);
            }
        });

        // Email Icon - Opens Email App with Predefined Address
        ImageView emailIcon = findViewById(R.id.iv_contact_email);
        emailIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:sursecodos@gmail.com")); // Email address
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry"); // Subject
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello, I have a question..."); // Default message
                startActivity(Intent.createChooser(emailIntent, "Send email using"));
            }
        });

        // Logo Icon - Optional Action (e.g., Navigate to Home or Show Toast)
        ImageView logoIcon = findViewById(R.id.iv_logo);
        logoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Example: Navigate to another activity or show a toast
                Intent intent = new Intent(ContactUsActivity.this, MainActivity.class); // Change to target activity
                startActivity(intent);
            }
        });
    }
}
