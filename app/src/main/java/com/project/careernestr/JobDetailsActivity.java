package com.project.careernestr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class JobDetailsActivity extends AppCompatActivity {

    Button applyBtn;
    ImageView backBtn, bookmarkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        // Initialize views
        applyBtn = findViewById(R.id.applyBtn);
        backBtn = findViewById(R.id.backBtn);
        bookmarkBtn = findViewById(R.id.bookmarkBtn);

        // 🔙 Back button
        backBtn.setOnClickListener(v -> finish());

        // 🔖 Bookmark (optional)
        bookmarkBtn.setOnClickListener(v -> {
            // You can add save logic later
        });

        // 🚀 Apply Button → Open Bdjobs
        applyBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.bdjobs.com"));
            startActivity(intent);
        });
    }
}