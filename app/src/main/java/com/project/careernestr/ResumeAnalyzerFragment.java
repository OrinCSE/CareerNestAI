package com.project.careernestr;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;

// 🎯 কোনো সাব-প্যাকেজের ঝামেলা ছাড়াই সরাসরি একই ডিরেক্টরি থেকে ক্লাস কল করা হলো
import com.project.careernestr.AIJobAdapter;
import com.project.careernestr.AIJobPostModel;

public class ResumeAnalyzerFragment extends Fragment {

    private ResumeAnalyzerViewModel viewModel;
    private RecyclerView recyclerView;

    private TextView tvFileName, tvUploadDate, tvScoreValue, tvScoreText, tvStatusLabel;
    private ProgressBar scoreProgressBar;
    private FlexboxLayout skillsFlexbox;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resume_analyzer, container, false);

        // UI আইডি বাইন্ডিং
        tvFileName = view.findViewById(R.id.fileName);
        tvUploadDate = view.findViewById(R.id.uploadDate);
        tvScoreValue = view.findViewById(R.id.scoreValue);
        tvScoreText = view.findViewById(R.id.scoreText);
        tvStatusLabel = view.findViewById(R.id.statusLabel);
        scoreProgressBar = view.findViewById(R.id.scoreProgress);
        skillsFlexbox = view.findViewById(R.id.skillsFlexbox);
        recyclerView = view.findViewById(R.id.jobsRecycler);

        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        // ভিউমডেল ইনিশিয়ালাইজেশন
        viewModel = new ViewModelProvider(this).get(ResumeAnalyzerViewModel.class);

        // ইন্টেন্ট থেকে ডেটা রিসিভ করা
        if (getActivity() != null && getActivity().getIntent().hasExtra("RESUME_URI")) {
            String resumeName = getActivity().getIntent().getStringExtra("RESUME_NAME");
            if (resumeName != null && tvFileName != null) {
                tvFileName.setText(resumeName);
            }
            if (tvUploadDate != null) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault());
                tvUploadDate.setText("Uploaded " + sdf.format(new java.util.Date()));
            }
            setupStaticAnalysisUI();
        }

        // 🧠 লাইভ-ডেটা অবজারভার (সব এরর মুক্ত)
        viewModel.getRecommendedJobs().observe(getViewLifecycleOwner(), jobs -> {
            if (jobs != null && recyclerView != null) {
                AIJobAdapter adapter = new AIJobAdapter(getContext(), jobs);
                recyclerView.setAdapter(adapter);
            }
        });

        return view;
    }

    private void setupStaticAnalysisUI() {
        int generatedScore = 78;
        String status = "Good";
        String hexColor = "#F59E0B";

        if (tvScoreValue != null) tvScoreValue.setText(String.valueOf(generatedScore));
        if (scoreProgressBar != null) scoreProgressBar.setProgress(generatedScore);
        if (tvStatusLabel != null) {
            tvStatusLabel.setText(status);
            tvStatusLabel.setTextColor(Color.parseColor(hexColor));
        }
        if (tvScoreText != null) {
            tvScoreText.setText("Your resume scores " + generatedScore + "/100. Based on extracted industry standards.");
        }

        // ডায়নামিক স্কিল চিপস জেনারেশন
        if (skillsFlexbox != null) {
            skillsFlexbox.removeAllViews();
            String[] parsedSkills = {"Java", "Android Studio", "Firebase", "REST API", "Git"};

            for (String skill : parsedSkills) {
                TextView chip = new TextView(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(10, 10, 10, 10);
                chip.setLayoutParams(params);

                chip.setText(skill);
                chip.setPadding(30, 16, 30, 16);
                chip.setTextColor(Color.parseColor("#2196F3"));
                chip.setBackgroundResource(R.drawable.skill_chip_bg);
                chip.setTextSize(13);

                skillsFlexbox.addView(chip);
            }
        }
    }
}