package com.project.careernestr;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BrowseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ১. নতুন তৈরি করা অ্যাক্টিভিটি লেআউট সেট করো
        setContentView(R.layout.activity_browse);

        // ২. রান টাইমে লজিক চেক করে সঠিক ফ্র্যাগমেন্ট লোড করো
        if (savedInstanceState == null) {
            // 🚀 যদি ফাইল আপলোড করে এই পেজে আসা হয় (Intent-এ ডেটা থাকে), তবে Analyzer Fragment লোড হবে
            if (getIntent().hasExtra("RESUME_URI")) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ResumeAnalyzerFragment())
                        .commit();
            } else {
                // নরমালি ব্রাউজ পেজে আসলে আগের মতো BrowseFragment ওপেন হবে
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new BrowseFragment())
                        .commit();
            }
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.nav_browse);

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startActivity(new Intent(BrowseActivity.this, Activity_dashboard.class));
                overridePendingTransition(0, 0);
                finish(); // ব্যাক স্ট্যাক ক্লিয়ার রাখার জন্য
                return true;
            } else if (itemId == R.id.nav_browse) {
                return true;
            } else if (itemId == R.id.nav_cv) {
                startActivity(new Intent(BrowseActivity.this, MyCVActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.nav_tracker) {
                startActivity(new Intent(BrowseActivity.this, TrackerActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.nav_skills) {
                startActivity(new Intent(BrowseActivity.this, SkillsActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return false;
        });
    }
}