package com.project.careernestr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrackerButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        // ১. বটম নেভিগেশন সেটআপ
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.nav_tracker);

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(this, Activity_dashboard.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_browse) {
                startActivity(new Intent(this, BrowseActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_cv) {
                startActivity(new Intent(this, MyCVActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_skills) {
                startActivity(new Intent(this, SkillsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return itemId == R.id.nav_tracker;
        });

        // ২. বাটন লজিক (উদাহরণস্বরূপ কার্ড ১ এর জন্য)
        findViewById(R.id.btnMarkInterview1).setOnClickListener(v ->
                Toast.makeText(this, "Marked as Interview!", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnReject1).setOnClickListener(v ->
                Toast.makeText(this, "Application Rejected", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnDelete1).setOnClickListener(v ->
                Toast.makeText(this, "Application Deleted", Toast.LENGTH_SHORT).show());
    }
}