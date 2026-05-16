package com.project.careernestr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import androidx.activity.OnBackPressedCallback;

public class Activity_dashboard extends AppCompatActivity {

    BottomNavigationView bottomNav;
    ImageView profileBtn, menuBtn;
    DrawerLayout drawerLayout;
    TextView tvSeeAll;

    CardView cardDataScience, cardDigitalMarketing, cardSoftwareEngineer;
    RelativeLayout uploadBtn;
    android.widget.Button applyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize views
        drawerLayout = findViewById(R.id.drawer_layout);
        uploadBtn = findViewById(R.id.uploadBtn);
        applyBtn = findViewById(R.id.applyBtn);
        bottomNav = findViewById(R.id.bottomNav);
        tvSeeAll = findViewById(R.id.tvSeeAll);

        cardDataScience = findViewById(R.id.cardDataScience);
        cardDigitalMarketing = findViewById(R.id.cardDigitalMarketing);
        cardSoftwareEngineer = findViewById(R.id.cardSoftwareEngineer);

        // Header view
        View headerView = findViewById(R.id.layoutHeaderTop);
        if (headerView != null) {
            profileBtn = headerView.findViewById(R.id.profile_image);
            menuBtn = headerView.findViewById(R.id.menu_icon);
        }

        // Profile click
        if (profileBtn != null) {
            profileBtn.setOnClickListener(v -> {
                startActivity(new Intent(this, ProfileActivity.class));
                SupabaseHelper.insertUser(
                        "Orin",
                        "orin@gmail.com"
                );
            });
        }

        // Menu click
        if (menuBtn != null) {
            menuBtn.setOnClickListener(v -> {
                if (drawerLayout != null) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }

        // See all click
        if (tvSeeAll != null) {
            tvSeeAll.setOnClickListener(v -> {
                startActivity(new Intent(this, DeadlineRemindersActivity.class));
            });
        }

        // Card click safety
        if (cardDataScience != null) {
            cardDataScience.setOnClickListener(v ->
                    Toast.makeText(this, "Opening Data Science Intern", Toast.LENGTH_SHORT).show()
            );
        }

        if (cardDigitalMarketing != null) {
            cardDigitalMarketing.setOnClickListener(v ->
                    Toast.makeText(this, "Digital Marketing Intern", Toast.LENGTH_SHORT).show()
            );
        }

        if (cardSoftwareEngineer != null) {
            cardSoftwareEngineer.setOnClickListener(v ->
                    Toast.makeText(this, "Software Engineer Intern", Toast.LENGTH_SHORT).show()
            );
        }

        if (uploadBtn != null) {
            uploadBtn.setOnClickListener(v ->
                    Toast.makeText(this, "Upload Resume Clicked", Toast.LENGTH_SHORT).show()
            );
        }

        if (applyBtn != null) {
            applyBtn.setOnClickListener(v ->
                    Toast.makeText(this, "Applied Successfully!", Toast.LENGTH_SHORT).show()
            );
        }

        // RecyclerView setup
        RecyclerView recyclerView = findViewById(R.id.recyclerViewJobs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<JobModel> jobList = new ArrayList<>();
        jobList.add(new JobModel("Mobile App Developer Intern", "Pathao", "BDT 15,000/month"));
        jobList.add(new JobModel("Software Engineer Intern", "Brain Station 23", "BDT 10,000/month"));
        jobList.add(new JobModel("Frontend Developer Intern", "Tiger IT", "BDT 12,000/month"));

        JobAdapter adapter = new JobAdapter(this, jobList);
        recyclerView.setAdapter(adapter);

        // Bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);

// ড্যাশবোর্ডে থাকা অবস্থায় Home বাটনটি সিলেক্টেড দেখাবে
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_browse) {
                startActivity(new Intent(Activity_dashboard.this, BrowseActivity.class));
                overridePendingTransition(0, 0); // পেজ চেঞ্জ হওয়ার সময় জিরকি অ্যানিমেশন বন্ধ করার জন্য
                return true;
            } else if (id == R.id.nav_cv) {
                startActivity(new Intent(Activity_dashboard.this, MyCVActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_tracker) {
                startActivity(new Intent(Activity_dashboard.this, TrackerActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_skills) {
                startActivity(new Intent(Activity_dashboard.this, SkillsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    finish();
                }
            }
        });
    }

    // Navigation helper
    private void navigateTo(Class<?> targetActivity) {
        startActivity(new Intent(this, targetActivity));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    // Back press fix (Drawer handling)

}