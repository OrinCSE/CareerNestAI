package com.project.careernestr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrowseFragment extends Fragment {

    private EditText searchBar;
    private Button btnSmart, btnAll;
    private ChipGroup filterChipGroup;
    private RecyclerView rvInternships;
    private ImageView filterIcon;

    private JobAdapter jobAdapter;
    private List<JobModel> masterJobList = new ArrayList<>();
    private List<JobModel> displayedJobList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_browse, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchBar = view.findViewById(R.id.searchBar);
        btnSmart = view.findViewById(R.id.btnSmart);
        btnAll = view.findViewById(R.id.btnAll);
        filterChipGroup = view.findViewById(R.id.filterChipGroup);
        rvInternships = view.findViewById(R.id.rvInternships);
        filterIcon = view.findViewById(R.id.filterIcon);

        rvInternships.setLayoutManager(new LinearLayoutManager(getContext()));
        rvInternships.setHasFixedSize(true);

        loadDemoData();

        jobAdapter = new JobAdapter(getContext(), displayedJobList);
        rvInternships.setAdapter(jobAdapter);

        btnSmart.setOnClickListener(v -> {
            btnSmart.setBackgroundResource(R.drawable.selected_toggle_btn);
            btnAll.setBackgroundResource(android.R.color.transparent);
            filterBySmartMatch();
        });

        btnAll.setOnClickListener(v -> {
            btnAll.setBackgroundResource(R.drawable.selected_toggle_btn);
            btnSmart.setBackgroundResource(android.R.color.transparent);
            resetList();
        });

        filterChipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (!checkedIds.isEmpty()) {
                int checkedId = checkedIds.get(0);
                Chip selectedChip = view.findViewById(checkedId);
                if (selectedChip != null) {
                    String category = selectedChip.getText().toString();
                    filterJobs(category);
                }
            } else {
                resetList();
            }
        });

        filterIcon.setOnClickListener(v -> {

            Toast.makeText(getContext(), "Filter clicked", Toast.LENGTH_SHORT).show();

            if (getActivity() != null && isAdded()) {

                FilterBottomSheet sheet = new FilterBottomSheet();

                // ইন্টারফেস লিসেনার অ্যাটাচ করা
                sheet.setFilterListener(new FilterBottomSheet.OnFilterAppliedListener() {
                    @Override
                    public void onFilterApplied(String type, String difficulty, String sortBy) {
                        updateJobList(type, difficulty, sortBy);
                    }
                });

                // BottomSheet show
                sheet.show(getActivity().getSupportFragmentManager(), "FilterBottomSheetTag");
            }
        });
    }

    private void loadDemoData() {
        masterJobList.clear();
        // 🚀 তোমার ড্যাশবোর্ডের রিয়াল স্ট্রাকচার অনুযায়ী ৭টি প্যারামিটার দিয়ে ব্রাউজ ফ্রাগমেন্টের ডেমো ডেটা তৈরি
        masterJobList.add(new JobModel("Mobile App Developer Intern", "Pathao", "Dhaka, Bangladesh", "BDT 15,000/month", "95", "onsite", "Intermediate"));
        masterJobList.add(new JobModel("Software Engineer Intern", "Brain Station 23", "Dhaka, Bangladesh", "BDT 10,000/month", "82", "onsite", "Hard"));
        masterJobList.add(new JobModel("Frontend Developer Intern", "Tiger IT", "Dhaka, Bangladesh", "BDT 12,000/month", "60", "onsite", "Hard"));

        displayedJobList.clear();
        displayedJobList.addAll(masterJobList);
    }

    private void filterJobs(String category) {
        displayedJobList.clear();
        for (JobModel job : masterJobList) {
            if (job.getJobTitle().toLowerCase().contains(category.toLowerCase()) ||
                    job.getLocation().toLowerCase().contains(category.toLowerCase())) {
                displayedJobList.add(job);
            }
        }
        jobAdapter.notifyDataSetChanged();
    }

    private void updateJobList(String type, String difficulty, String sortBy) {
        displayedJobList.clear();

        for (JobModel job : masterJobList) {
            boolean matchesType = type.isEmpty() || job.getJobType().equalsIgnoreCase(type);
            boolean matchesDifficulty = difficulty.isEmpty() || job.getLevel().equalsIgnoreCase(difficulty);

            if (matchesType && matchesDifficulty) {
                displayedJobList.add(job);
            }
        }

        if (sortBy.equalsIgnoreCase("Best Match")) {
            Collections.sort(displayedJobList, (j1, j2) -> Integer.compare(j2.getMatchPercentageAsInt(), j1.getMatchPercentageAsInt()));
        } else if (sortBy.equalsIgnoreCase("Difficulty")) {
            Collections.sort(displayedJobList, (j1, j2) -> j1.getLevel().compareToIgnoreCase(j2.getLevel()));
        }

        jobAdapter.notifyDataSetChanged();
    }

    private void filterBySmartMatch() {
        displayedJobList.clear();
        for (JobModel job : masterJobList) {
            if (job.getMatchPercentageAsInt() >= 80) {
                displayedJobList.add(job);
            }
        }
        jobAdapter.notifyDataSetChanged();
    }

    private void resetList() {
        displayedJobList.clear();
        displayedJobList.addAll(masterJobList);
        jobAdapter.notifyDataSetChanged();
    }
}