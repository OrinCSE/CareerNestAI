package com.project.careernestr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class JobDetailsActivity extends AppCompatActivity {
    Button applyBtn;
    ImageView backBtn, bookmarkBtn;

    TextView tvJobTitle;
    TextView tvCompanyName;
    TextView tvMatchScore;
    TextView tvLocation;
    TextView tvStipend;
    TextView tagOnsite;
    TextView tagLevel;

    JobModel selectedJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        applyBtn = findViewById(R.id.applyBtn);
        backBtn = findViewById(R.id.backBtn);
        bookmarkBtn = findViewById(R.id.bookmarkBtn);

        tvJobTitle = findViewById(R.id.tvJobTitleDetails);
        tvCompanyName = findViewById(R.id.tvCompanyDetails);
        tvMatchScore = findViewById(R.id.tvMatchScoreDetails);
        tvLocation = findViewById(R.id.tvLocationDetails);
        tvStipend = findViewById(R.id.tvStipendDetails);

        tagOnsite = findViewById(R.id.tagOnsite);
        tagLevel = findViewById(R.id.tagLevel);

        if (getIntent().hasExtra("SELECTED_JOB")) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                selectedJob = getIntent().getSerializableExtra("SELECTED_JOB", JobModel.class);
            } else {
                selectedJob = (JobModel) getIntent().getSerializableExtra("SELECTED_JOB");
            }

            if (selectedJob != null) {
                // Set Dynamic Data
                if (tvJobTitle != null) tvJobTitle.setText(selectedJob.getJobTitle());
                if (tvCompanyName != null) tvCompanyName.setText(selectedJob.getCompanyName());
                if (tvMatchScore != null) tvMatchScore.setText(selectedJob.getMatchScore() + "% Match");
                if (tvLocation != null) tvLocation.setText(selectedJob.getLocation());
                if (tvStipend != null) tvStipend.setText(selectedJob.getStipend() + "/month");
                if (tagOnsite != null) tagOnsite.setText(selectedJob.getJobType());
                if (tagLevel != null) tagLevel.setText(selectedJob.getLevel());
            }
        }

        // =========================
        // Back Button
        // =========================
        backBtn.setOnClickListener(v -> finish());

        // =========================
        // Bookmark Button
        // =========================
        bookmarkBtn.setOnClickListener(v -> {
            // Future Save Logic Here
        });

        // =========================
        // Apply Button (Bdjobs থিম ও লিংক ইনট্যাক্ট রাখা হয়েছে)
        // =========================
        applyBtn.setOnClickListener(v -> {
            String applyLink = "https://www.bdjobs.com";

            // মডেল থেকে লিংক রিসিভ করার মেথড সেফটি চেক
            if (selectedJob != null && selectedJob.getApplyLink() != null) {
                applyLink = selectedJob.getApplyLink();
            }

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(applyLink));
            startActivity(intent);
        });
    }
}