package com.project.careernestr;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SkillsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // নিশ্চিত হয়ে নিন লেআউট ফাইলটির নাম ঠিক আছে কি না
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        // ১. যেহেতু এটি Skills পেজ, তাই Skills আইকনটি সিলেক্টেড থাকবে
        bottomNav.setSelectedItemId(R.id.nav_skills);

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startActivity(new Intent(SkillsActivity.this, Activity_dashboard.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_browse) {
                startActivity(new Intent(SkillsActivity.this, BrowseActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_cv) {
                startActivity(new Intent(SkillsActivity.this, MyCVActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_tracker) {
                startActivity(new Intent(SkillsActivity.this, TrackerActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_skills) {
                // অলরেডি Skills পেজেই আছেন, তাই নতুন করে লোড করার দরকার নেই
                return true;
            }
            return false;
        });
    }
}