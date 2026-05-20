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

    private androidx.activity.result.ActivityResultLauncher<Intent> filePickerLauncher;
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

        // ওল্ড ফাইল পিকার লাঞ্চারটি যেভাবে ছিল সেভাবেই রাখা হলো (কোনো ক্র্যাশ এভোয়েড করার জন্য)
        filePickerLauncher = registerForActivityResult(
                new androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == android.app.Activity.RESULT_OK && result.getData() != null) {
                        android.net.Uri fileUri = result.getData().getData();
                        if (fileUri != null) {
                            try {
                                java.io.InputStream inputStream = getContentResolver().openInputStream(fileUri);
                                if (inputStream != null) {
                                    byte[] fileData = new byte[inputStream.available()];
                                    inputStream.read(fileData);
                                    inputStream.close();
                                    String fileName = "resume_" + System.currentTimeMillis() + ".pdf";
                                    Toast.makeText(Activity_dashboard.this, "Uploading file, please wait...", Toast.LENGTH_SHORT).show();
                                    SupabaseHelper.uploadResume(fileName, fileData, Activity_dashboard.this, null);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(Activity_dashboard.this, "Failed to read file data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );

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

        // 🚀 ফিক্সড লজিক: এখন "Upload Your Resume" বক্সে ক্লিক করলে সরাসরি ফাইল আপলোড ইন্টারফেসে নিয়ে যাবে
        if (uploadBtn != null) {
            uploadBtn.setOnClickListener(v -> {
                Intent intent = new Intent(Activity_dashboard.this, ResumeUploadActivity.class);
                startActivity(intent);
            });
        }

        if (applyBtn != null) {
            applyBtn.setOnClickListener(v ->
                    Toast.makeText(this, "Applied Successfully!", Toast.LENGTH_SHORT).show()
            );
        }

        // Job Recycler Setup
        RecyclerView recyclerView = findViewById(R.id.recyclerViewJobs);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            List<JobModel> jobList = new ArrayList<>();
            jobList.add(new JobModel("Mobile App Developer Intern", "Pathao", "Dhaka, Bangladesh", "BDT 15,000/month", "50", "onsite", "Intermediate"));
            jobList.add(new JobModel("Software Engineer Intern", "Brain Station 23", "Dhaka, Bangladesh", "BDT 10,000/month", "50", "onsite", "Hard"));
            jobList.add(new JobModel("Frontend Developer Intern", "Tiger IT", "Dhaka, Bangladesh", "BDT 12,000/month", "50", "onsite", "Hard"));

            JobAdapter adapter = new JobAdapter(this, jobList);
            recyclerView.setAdapter(adapter);
        }

        // Bottom navigation handling
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(R.id.nav_home);

            bottomNav.setOnItemSelectedListener(item -> {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    return true;
                } else if (id == R.id.nav_browse) {
                    startActivity(new Intent(Activity_dashboard.this, BrowseActivity.class));
                    overridePendingTransition(0, 0);
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
        }

        // Back button filter
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    finishAffinity();
                }
            }
        });
    }

    private void navigateTo(Class<?> targetActivity) {
        startActivity(new Intent(this, targetActivity));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}