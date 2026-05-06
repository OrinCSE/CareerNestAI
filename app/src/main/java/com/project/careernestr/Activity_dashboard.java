package com.project.careernestr;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ImageView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Activity_dashboard extends AppCompatActivity {

    LinearLayout uploadBtn;
    Button applyBtn;
    BottomNavigationView bottomNav;

    ImageView profileBtn, menuBtn;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        uploadBtn = findViewById(R.id.uploadBtn);
        applyBtn = findViewById(R.id.applyBtn);
        bottomNav = findViewById(R.id.bottomNav);

        // ✅ New Added Views
        profileBtn = findViewById(R.id.profile_image);
        menuBtn = findViewById(R.id.menu_icon);
        drawerLayout = findViewById(R.id.drawer_layout);

        // ✅ Go to Profile
        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(activity_dashboard.this, ProfileActivity.class);
            startActivity(intent);
        });

        // ✅ Open Drawer
        menuBtn.setOnClickListener(v -> {
            if (drawerLayout != null && !drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });

        // Upload Resume Click
        uploadBtn.setOnClickListener(view ->
                Toast.makeText(Activity_dashboard.this, "Upload Resume Clicked", Toast.LENGTH_SHORT).show()
        );

        // Apply Button Click
        applyBtn.setOnClickListener(view ->
                Toast.makeText(Activity_dashboard.this, "Applied Successfully!", Toast.LENGTH_SHORT).show()
        );

        // RecyclerView Setup
        RecyclerView recyclerView = findViewById(R.id.recyclerViewJobs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<JobModel> jobList = new ArrayList<>();
        jobList.add(new JobModel("Mobile App Developer Intern", "Pathao", "BDT 15,000/month"));
        jobList.add(new JobModel("Software Engineer Intern", "Brain Station 23", "BDT 10,000/month"));
        jobList.add(new JobModel("Frontend Developer Intern", "Tiger IT", "BDT 12,000/month"));

        JobAdapter adapter = new JobAdapter(this, jobList);
        recyclerView.setAdapter(adapter);

        // Bottom Navigation Clicks
        bottomNav.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_home) {
                return true;

            } else if (item.getItemId() == R.id.nav_browse) {
                Toast.makeText(this, "Browse clicked", Toast.LENGTH_SHORT).show();
                return true;

            } else if (item.getItemId() == R.id.nav_cv) {
                Toast.makeText(this, "My CV clicked", Toast.LENGTH_SHORT).show();
                return true;

            } else if (item.getItemId() == R.id.nav_tracker) {
                Toast.makeText(this, "Tracker clicked", Toast.LENGTH_SHORT).show();
                return true;

            } else if (item.getItemId() == R.id.nav_skills) {
                Toast.makeText(this, "Skills clicked", Toast.LENGTH_SHORT).show();
                return true;
            }

            return false;
        });
    }
}