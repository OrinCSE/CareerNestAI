package com.project.careernestr; // আপনার প্রজেক্টের সঠিক প্যাকেজ নাম দিন

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class FilterBottomSheet extends BottomSheetDialogFragment {

    // ইন্টারফেসটি ক্লাসের ভেতরেই ডিফাইন করুন যাতে কনফ্লিক্ট না হয়
    public interface FilterListener {
        void onFiltersApplied(String type, String difficulty, String sortBy);
    }

    private ChipGroup typeGroup, difficultyGroup, sortByGroup;
    private Button btnApply;
    private FilterListener listener;

    public void setFilterListener(FilterListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_filter_sheet, container, false);

        typeGroup = v.findViewById(R.id.typeGroup);
        difficultyGroup = v.findViewById(R.id.difficultyGroup);
        sortByGroup = v.findViewById(R.id.sortByGroup);
        btnApply = v.findViewById(R.id.btnApply);

        btnApply.setOnClickListener(view -> {
            String selectedType = getSelectedChipText(typeGroup);
            String selectedDifficulty = getSelectedChipText(difficultyGroup);
            String selectedSort = getSelectedChipText(sortByGroup);

            if (listener != null) {
                listener.onFiltersApplied(selectedType, selectedDifficulty, selectedSort);
            }
            dismiss();
        });

        return v;
    }

    private String getSelectedChipText(ChipGroup group) {
        int chipId = group.getCheckedChipId();
        if (chipId != View.NO_ID) {
            Chip chip = group.findViewById(chipId);
            return chip.getText().toString();
        }
        return "All";
    }
}