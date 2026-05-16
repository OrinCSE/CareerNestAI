package com.project.careernestr;

import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.chip.Chip;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ResumeAnalyzerFragment extends Fragment {

    // =========================
    // UI COMPONENTS
    // =========================

    private ProgressBar scoreProgressBar;

    private TextView scoreTextView;
    private TextView statusLabel;
    private TextView fileNameText;
    private TextView uploadDateText;

    private FlexboxLayout skillsFlexbox;

    private View uploadCard;
    private View btnDelete;
    private View btnRefresh;

    // =========================
    // FILE PICKER
    // =========================

    private ActivityResultLauncher<String> filePickerLauncher;

    // =========================
    // LIFECYCLE
    // =========================

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // File Picker Launcher

        filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        handleResumeUpload(uri);
                    }
                }
        );
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        View view = inflater.inflate(
                R.layout.fragment_resume_analyzer,
                container,
                false
        );

        initializeViews(view);

        setupClickListeners();

        return view;
    }

    // =========================
    // INITIALIZE VIEWS
    // =========================

    private void initializeViews(View view) {

        scoreProgressBar = view.findViewById(R.id.scoreProgress);

        scoreTextView = view.findViewById(R.id.scoreText);

        statusLabel = view.findViewById(R.id.statusLabel);

        fileNameText = view.findViewById(R.id.fileName);

        uploadDateText = view.findViewById(R.id.uploadDate);

        skillsFlexbox = view.findViewById(R.id.skillsFlexbox);

        uploadCard = view.findViewById(R.id.uploadCard);

        btnDelete = view.findViewById(R.id.btnDelete);

        btnRefresh = view.findViewById(R.id.btnRefresh);
    }

    // =========================
    // CLICK LISTENERS
    // =========================

    private void setupClickListeners() {

        // Upload Resume

        uploadCard.setOnClickListener(v ->
                filePickerLauncher.launch("application/pdf")
        );

        // Delete Resume

        btnDelete.setOnClickListener(v -> {

            resetUI();

            Toast.makeText(
                    requireContext(),
                    "Resume deleted successfully",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // Refresh AI Analysis

        btnRefresh.setOnClickListener(v -> {

            Toast.makeText(
                    requireContext(),
                    "Re-analyzing resume...",
                    Toast.LENGTH_SHORT
            ).show();

            // Mock refresh analysis

            Random random = new Random();

            int refreshedScore = 45 + random.nextInt(45);

            updateScoreUI(refreshedScore);
        });
    }

    // =========================
    // HANDLE RESUME UPLOAD
    // =========================

    private void handleResumeUpload(Uri uri) {

        // Get File Name

        String resumeName = getFileName(uri);

        fileNameText.setText(resumeName);

        uploadDateText.setText("Uploaded Just Now");

        // =========================
        // MOCK AI ANALYSIS
        // =========================

        Random random = new Random();

        int aiGeneratedScore = 40 + random.nextInt(55);

        // Update Score

        updateScoreUI(aiGeneratedScore);

        // =========================
        // MOCK AI SKILLS
        // =========================

        List<String> extractedSkills = Arrays.asList(
                "Ethical Hacking",
                "Network Security",
                "Java",
                "Android Studio",
                "Figma",
                "Firebase",
                "Cyber Security",
                "UX Research",
                "GraphQL",
                "Pandas"
        );

        displaySkills(extractedSkills);

        Toast.makeText(
                requireContext(),
                "Resume analyzed successfully",
                Toast.LENGTH_SHORT
        ).show();
    }

    // =========================
    // UPDATE SCORE UI
    // =========================

    private void updateScoreUI(int score) {

        // Progress Animation

        ObjectAnimator progressAnimator =
                ObjectAnimator.ofInt(
                        scoreProgressBar,
                        "progress",
                        0,
                        score
                );

        progressAnimator.setDuration(1200);

        progressAnimator.start();

        // Score Text

        scoreTextView.setText(
                "Your resume scores "
                        + score
                        + "/100. AI analysis completed."
        );

        // =========================
        // SCORE STATUS LOGIC
        // =========================

        String status;

        int color;

        if (score <= 40) {

            status = "Poor";

            color = Color.RED;

        } else if (score <= 60) {

            status = "Fair";

            color = Color.parseColor("#F59E0B");

        } else if (score <= 80) {

            status = "Good";

            color = Color.parseColor("#2196F3");

        } else {

            status = "Excellent";

            color = Color.parseColor("#16A34A");
        }

        // Apply UI Changes

        statusLabel.setText(status);

        statusLabel.setTextColor(color);

        scoreProgressBar.getProgressDrawable().setTint(color);
    }

    // =========================
    // DISPLAY SKILLS
    // =========================

    private void displaySkills(List<String> skills) {

        skillsFlexbox.removeAllViews();

        for (String skill : skills) {

            Chip chip = new Chip(requireContext());

            chip.setText(skill);

            // Styling

            chip.setTextColor(
                    Color.parseColor("#2196F3")
            );

            chip.setChipBackgroundColor(
                    ColorStateList.valueOf(
                            Color.parseColor("#E3F2FD")
                    )
            );

            chip.setChipStrokeWidth(1.5f);

            chip.setChipStrokeColor(
                    ColorStateList.valueOf(
                            Color.parseColor("#BBDEFB")
                    )
            );

            chip.setChipCornerRadius(50f);

            chip.setClickable(false);

            chip.setCheckable(false);

            chip.setTextSize(13f);

            // Layout Margins

            FlexboxLayout.LayoutParams params =
                    new FlexboxLayout.LayoutParams(
                            FlexboxLayout.LayoutParams.WRAP_CONTENT,
                            FlexboxLayout.LayoutParams.WRAP_CONTENT
                    );

            params.setMargins(10, 10, 10, 10);

            chip.setLayoutParams(params);

            // Add Chip

            skillsFlexbox.addView(chip);
        }
    }

    // =========================
    // RESET UI
    // =========================

    private void resetUI() {

        scoreProgressBar.setProgress(0);

        scoreTextView.setText(
                "Upload a resume to see AI analysis"
        );

        statusLabel.setText("");

        fileNameText.setText("No Resume Uploaded");

        uploadDateText.setText("");

        skillsFlexbox.removeAllViews();
    }

    // =========================
    // GET FILE NAME
    // =========================

    private String getFileName(Uri uri) {

        String result = "resume.pdf";

        Cursor cursor = requireActivity()
                .getContentResolver()
                .query(
                        uri,
                        null,
                        null,
                        null,
                        null
                );

        try {

            if (cursor != null && cursor.moveToFirst()) {

                int nameIndex =
                        cursor.getColumnIndex(
                                OpenableColumns.DISPLAY_NAME
                        );

                if (nameIndex >= 0) {

                    result = cursor.getString(nameIndex);
                }
            }

        } finally {

            if (cursor != null) {
                cursor.close();
            }
        }

        return result;
    }
}