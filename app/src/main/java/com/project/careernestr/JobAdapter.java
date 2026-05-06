package com.project.careernestr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    Context context;
    List<JobModel> jobList;
    List<JobModel> fullList;

    public JobAdapter(Context context, List<JobModel> jobList) {
        this.context = context;
        this.jobList = jobList;
        this.fullList = new ArrayList<>(jobList); // copy original list
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_job, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        JobModel job = jobList.get(position);

        holder.title.setText(job.getTitle());
        holder.company.setText(job.getCompany());
        holder.salary.setText(job.getSalary());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    // 🔥 THIS IS THE IMPORTANT PART (SEARCH FILTER)
    public void filter(String text) {

        jobList.clear();

        if (text.isEmpty()) {
            jobList.addAll(fullList);
        } else {
            text = text.toLowerCase();

            for (JobModel job : fullList) {
                if (job.getTitle().toLowerCase().contains(text)
                        || job.getCompany().toLowerCase().contains(text)) {

                    jobList.add(job);
                }
            }
        }

        notifyDataSetChanged(); // refresh RecyclerView
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, company, salary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.jobTitle);
            company = itemView.findViewById(R.id.companyName);
            salary = itemView.findViewById(R.id.jobSalary);
        }
    }
}