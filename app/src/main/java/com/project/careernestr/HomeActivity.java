package com.project.careernestr;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity; // এটি নিশ্চিত করবে Manifest এর এরর চলে যাবে
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard); // আপনার হোম লেআউট দিন

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_browse) {
                startActivity(new Intent(HomeActivity.this, BrowseActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_cv) {
                startActivity(new Intent(HomeActivity.this, MyCVActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_tracker) {
                startActivity(new Intent(HomeActivity.this, TrackerActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.nav_skills) {
                startActivity(new Intent(HomeActivity.this, SkillsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }
}