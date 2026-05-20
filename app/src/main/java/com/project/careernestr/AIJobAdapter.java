package com.project.careernestr; // এটি যদি adapter ফোল্ডারে থাকে তবে এটাই থাকবে

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.project.careernestr.R;
import com.project.careernestr.AIJobPostModel; // মেইন ফোল্ডার থেকে মডেল ইমপোর্ট

import java.util.List;

public class AIJobAdapter extends RecyclerView.Adapter<AIJobAdapter.AIJobViewHolder> {

    private final Context context;
    private final List<AIJobPostModel> jobList;

    public AIJobAdapter(Context context, List<AIJobPostModel> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public AIJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ai_recommended_job, parent, false);
        return new AIJobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AIJobViewHolder holder, int position) {
        AIJobPostModel job = jobList.get(position);
        holder.tvJobTitle.setText(job.getJobTitle());
        holder.tvCompany.setText(job.getCompanyName());
        holder.tvDate.setText(job.getPostedDate());
        holder.tvExplanation.setText(job.getMatchExplanation());

        int matchPercent = job.getMatchPercentage();
        holder.tvPercent.setText(matchPercent + "%");
        holder.progressBar.setProgress(matchPercent);

        if (matchPercent >= 90) {
            holder.tvPercent.setTextColor(Color.parseColor("#16A34A"));
        } else if (matchPercent >= 70) {
            holder.tvPercent.setTextColor(Color.parseColor("#EA580C"));
        } else {
            holder.tvPercent.setTextColor(Color.parseColor("#DC2626"));
        }

        holder.btnApply.setOnClickListener(v -> {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(job.getApplicationUrl()));
        });
    }

    @Override
    public int getItemCount() { return jobList.size(); }

    static class AIJobViewHolder extends RecyclerView.ViewHolder {
        TextView tvJobTitle, tvCompany, tvPercent, tvDate, tvExplanation;
        ProgressBar progressBar;
        MaterialButton btnApply;

        public AIJobViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJobTitle = itemView.findViewById(R.id.tvJobTitle);
            tvCompany = itemView.findViewById(R.id.tvCompany);
            tvPercent = itemView.findViewById(R.id.tvMatchPercentage);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvExplanation = itemView.findViewById(R.id.tvExplanation);
            progressBar = itemView.findViewById(R.id.jobProgress);
            btnApply = itemView.findViewById(R.id.btnApply);
        }
    }
}