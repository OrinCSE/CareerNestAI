package com.project.careernestr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DeadlineAdapter extends RecyclerView.Adapter<DeadlineAdapter.ViewHolder> {

    private Context context;
    private List<DeadlineModel> deadlineList;

    public DeadlineAdapter(Context context, List<DeadlineModel> deadlineList) {
        this.context = context;
        this.deadlineList = deadlineList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // নিশ্চিত করুন item_deadline ফাইলটি আপনার layout ফোল্ডারে আছে
        View view = LayoutInflater.from(context).inflate(R.layout.item_deadline, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // সরাসরি লিস্ট থেকে ডেটা নিয়ে সেট করা
        holder.jobTitle.setText(deadlineList.get(position).getJobTitle()+ " " + position);
        holder.companyName.setText(deadlineList.get(position).getCompanyName());
        holder.dateText.setText(deadlineList.get(position).getDate());
        holder.daysCount.setText(deadlineList.get(position).getDaysLeft());

        holder.sideBar.setBackgroundColor(deadlineList.get(position).getSideColor());
    }

    @Override
    public int getItemCount() {
        return deadlineList != null ? deadlineList.size() : 0;
    }

    // ✅ViewHolder ক্লাসটি এখন একদম ক্লিন এবং এরর-মুক্ত
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView jobTitle, companyName, dateText, daysCount;
        View sideBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jobTitle = itemView.findViewById(R.id.jobTitle);
            companyName = itemView.findViewById(R.id.companyName);
            dateText = itemView.findViewById(R.id.dateText);
            daysCount = itemView.findViewById(R.id.daysCount);
            sideBar = itemView.findViewById(R.id.sideBar);
        }
    }
}