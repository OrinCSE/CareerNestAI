package com.project.careernestr;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity; //
import com.google.android.material.bottomnavigation.BottomNavigationView; //

public class TrackerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker); //

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav); //

        // ১. এটি Tracker পেজ, তাই Tracker আইকনটি হাইলাইট হবে
        bottomNav.setSelectedItemId(R.id.nav_tracker); //

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startActivity(new Intent(TrackerActivity.this, HomeActivity.class));
                overridePendingTransition(0, 0); //
                return true;
            } else if (itemId == R.id.nav_browse) {
                startActivity(new Intent(TrackerActivity.this, BrowseActivity.class));
                overridePendingTransition(0, 0); //
                return true;
            } else if (itemId == R.id.nav_cv) {
                startActivity(new Intent(TrackerActivity.this, MyCVActivity.class));
                overridePendingTransition(0, 0); //
                return true;
            } else if (itemId == R.id.nav_tracker) {
                // অলরেডি Tracker পেজেই আছেন, তাই কিছু করার দরকার নেই
                return true;
            } else if (itemId == R.id.nav_skills) {
                startActivity(new Intent(TrackerActivity.this, SkillsActivity.class));
                overridePendingTransition(0, 0); //
                return true;
            }
            return false;
        });
    }
}