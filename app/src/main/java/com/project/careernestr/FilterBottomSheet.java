package com.project.careernestr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.button.MaterialButton;
import java.util.List;

public class FilterBottomSheet extends BottomSheetDialogFragment {

    public interface OnFilterAppliedListener {
        void onFilterApplied(String type, String difficulty, String sortBy);
    }

    private OnFilterAppliedListener filterListener;
    private ChipGroup typeGroup, difficultyGroup, sortByGroup;
    private MaterialButton btnApply;

    public void setFilterListener(OnFilterAppliedListener listener) {
        this.filterListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 🚀 এক্সএমএল ফাইলের সঠিক নাম লিংক করা হলো
        return inflater.inflate(R.layout.layout_filter_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ভিউ কাস্টিং ও আইডি লিংকিং
        typeGroup = view.findViewById(R.id.typeGroup);
        difficultyGroup = view.findViewById(R.id.difficultyGroup);
        sortByGroup = view.findViewById(R.id.sortByGroup);
        btnApply = view.findViewById(R.id.btnApply);

        if (btnApply != null) {
            btnApply.setOnClickListener(v -> {
                String selectedType = getSelectedChipText(typeGroup);
                String selectedDifficulty = getSelectedChipText(difficultyGroup);
                String selectedSortBy = getSelectedChipText(sortByGroup);

                if (filterListener != null) {
                    filterListener.onFilterApplied(selectedType, selectedDifficulty, selectedSortBy);
                }
                dismiss();
            });
        }
    }

    private String getSelectedChipText(ChipGroup chipGroup) {
        if (chipGroup == null) return "";
        List<Integer> checkedIds = chipGroup.getCheckedChipIds();
        if (checkedIds != null && !checkedIds.isEmpty()) {
            Chip chip = chipGroup.findViewById(checkedIds.get(0));
            if (chip != null) {
                return chip.getText().toString();
            }
        }
        return "";
    }
}