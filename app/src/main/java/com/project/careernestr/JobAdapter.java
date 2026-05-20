package com.project.careernestr;

import android.content.Context;
import android.content.Intent;
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
        this.fullList = new ArrayList<>(jobList);
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
        JobModel currentJob = jobList.get(position);

        if (holder.title != null && currentJob.getJobTitle() != null) {
            holder.title.setText(currentJob.getJobTitle());
        }
        if (holder.company != null && currentJob.getCompanyName() != null) {
            holder.company.setText(currentJob.getCompanyName());
        }
        if (holder.salary != null && currentJob.getStipend() != null) {
            holder.salary.setText(currentJob.getStipend());
        }

        // আইটেম ক্লিক লজিক (ইনটেন্টে অবজেক্ট পাস)
        holder.itemView.setOnClickListener(v -> {
            Context ctx = v.getContext();
            Intent intent = new Intent(ctx, JobDetailsActivity.class);

            // পুরো জব অবজেক্টটি ইনটেন্টে পুশ করা হচ্ছে (Serializable ইন্টারফেসের মাধ্যমে)
            intent.putExtra("SELECTED_JOB", currentJob);

            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    // 🚀 ফিক্স: সার্চ ফিল্টারের ভেতরেও নতুন গেটার মেথড বসানো হলো
    public void filter(String text) {
        jobList.clear();
        if (text.isEmpty()) {
            jobList.addAll(fullList);
        } else {
            text = text.toLowerCase();
            for (JobModel job : fullList) {
                if ((job.getJobTitle() != null && job.getJobTitle().toLowerCase().contains(text))
                        || (job.getCompanyName() != null && job.getCompanyName().toLowerCase().contains(text))) {
                    jobList.add(job);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, company, salary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // তোমার item_job.xml এর আইডি অনুযায়ী ভিউ চেনা
            title = itemView.findViewById(R.id.tvJobTitle);
            company = itemView.findViewById(R.id.tvCompanyName);
            salary = itemView.findViewById(R.id.tvSalary);
        }
    }
}