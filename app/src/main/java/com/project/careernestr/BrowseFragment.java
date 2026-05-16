package com.project.careernestr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import android.widget.Button;

public class BrowseFragment extends Fragment {

    private EditText searchBar;
    private Button btnSmart, btnAll;
    private ChipGroup filterChipGroup;
    private RecyclerView rvInternships;
    private ImageView filterIcon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // ভিউ ইনফ্লেট করা
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

        btnSmart.setOnClickListener(v -> {
            btnSmart.setBackgroundResource(R.drawable.selected_toggle_btn);
            btnAll.setBackgroundResource(android.R.color.transparent);
        });

        btnAll.setOnClickListener(v -> {
            btnAll.setBackgroundResource(R.drawable.selected_toggle_btn);
            btnSmart.setBackgroundResource(android.R.color.transparent);
        });

        filterChipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (!checkedIds.isEmpty()) {
                int checkedId = checkedIds.get(0);
                Chip selectedChip = view.findViewById(checkedId);
                String category = selectedChip.getText().toString();
                filterJobs(category);
            }
        });

        filterIcon.setOnClickListener(v -> {
            FilterBottomSheet sheet = new FilterBottomSheet();

            sheet.setFilterListener((type, difficulty, sortBy) -> {
                updateJobList(type, difficulty, sortBy);
            });

            sheet.show(getChildFragmentManager(), "FilterSheet");
        });
    }

    private void filterJobs(String category) {
        // এখানে Horizontal চিপস অনুযায়ী ফিল্টার হবে
    }

    private void updateJobList(String type, String difficulty, String sortBy) {
        // এখানে BottomSheet এর ফিল্টার অনুযায়ী ডাটা আপডেট হবে
    }
}