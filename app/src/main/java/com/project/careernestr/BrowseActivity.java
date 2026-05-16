package com.project.careernestr;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BrowseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // আপনার Browse পেজের সঠিক XML লেআউট ফাইলের নাম দিন
        // যদি এটি activity_browse হয়, তবে সেটিই দিন
        setContentView(R.layout.fragment_browse);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        // ১. যেহেতু এটি Browse পেজ, তাই Browse আইকনটি হাইলাইট থাকতে হবে
        bottomNav.setSelectedItemId(R.id.nav_browse);

        // ২. নেভিগেশন লজিক
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startActivity(new Intent(BrowseActivity.this, HomeActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_browse) {
                // আপনি অলরেডি এই পেজেই আছেন, তাই আবার লোড করার দরকার নেই
                return true;
            } else if (itemId == R.id.nav_cv) {
                startActivity(new Intent(BrowseActivity.this, MyCVActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_tracker) {
                startActivity(new Intent(BrowseActivity.this, TrackerActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_skills) {
                startActivity(new Intent(BrowseActivity.this, SkillsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }
}