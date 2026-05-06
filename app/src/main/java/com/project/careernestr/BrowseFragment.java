package com.project.careernestr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView; // ImageView ইম্পোর্ট করতে হবে
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
    private ImageView filterIcon; // ফিল্টার আইকন ডিক্লেয়ার করলাম

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // ভিউ ইনফ্লেট করা
        return inflater.inflate(R.layout.fragment_browse, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ১. ভিউগুলো খুঁজে বের করা (Initialize Views)
        searchBar = view.findViewById(R.id.searchBar);
        btnSmart = view.findViewById(R.id.btnSmart);
        btnAll = view.findViewById(R.id.btnAll);
        filterChipGroup = view.findViewById(R.id.filterChipGroup);
        rvInternships = view.findViewById(R.id.rvInternships);
        filterIcon = view.findViewById(R.id.filterIcon); // ফিল্টার আইকন ইনিশিয়ালাইজ

        // ২. RecyclerView সেটআপ করা
        rvInternships.setLayoutManager(new LinearLayoutManager(getContext()));

        // ৩. Smart/All টগল বাটনের লজিক
        btnSmart.setOnClickListener(v -> {
            btnSmart.setBackgroundResource(R.drawable.selected_toggle_btn);
            btnAll.setBackgroundResource(android.R.color.transparent);
        });

        btnAll.setOnClickListener(v -> {
            btnAll.setBackgroundResource(R.drawable.selected_toggle_btn);
            btnSmart.setBackgroundResource(android.R.color.transparent);
        });

        // ৪. ক্যাটাগরি চিপস ফিল্টারিং লজিক (Horizontal Scroll)
        filterChipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (!checkedIds.isEmpty()) {
                int checkedId = checkedIds.get(0);
                Chip selectedChip = view.findViewById(checkedId);
                String category = selectedChip.getText().toString();
                filterJobs(category);
            }
        });

        // ৫. আপনার নতুন ফিল্টার প্যানেল (BottomSheet) এর লজিক
        filterIcon.setOnClickListener(v -> {
            FilterBottomSheet sheet = new FilterBottomSheet();

            // লিসেনার সেট করা (BottomSheet থেকে ডাটা এখানে আসবে)
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