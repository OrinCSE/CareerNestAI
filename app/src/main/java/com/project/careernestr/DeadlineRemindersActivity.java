package com.project.careernestr;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DeadlineRemindersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadline_reminders);

        // ১. ব্যাক বাটন সেটআপ
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // ২. রিসাইক্লার ভিউ খুঁজে বের করা
        RecyclerView rvDeadlines = findViewById(R.id.rvDeadlines);
        rvDeadlines.setHasFixedSize(true);
        rvDeadlines.setLayoutManager(new LinearLayoutManager(this)); // এটি খুব গুরুত্বপূর্ণ

        // ৩. ডামি ডেটা তৈরি করা (যা স্ক্রিনশটে ছিল)
        List<DeadlineModel> list = new ArrayList<>();
        list.add(new DeadlineModel("Data Science Intern", "Shohoz", "May 10, 2026", "2", Color.parseColor("#EB5757")));
        list.add(new DeadlineModel("Digital Marketing Intern", "Grameenphone", "May 12, 2026", "4", Color.parseColor("#F2994A")));
        list.add(new DeadlineModel("Software Engineer Intern", "Brain Station 23", "May 15, 2026", "7", Color.parseColor("#F2C94C")));

        // ৪. অ্যাডাপ্টার সেট করা
        DeadlineAdapter adapter = new DeadlineAdapter(this, list);
        rvDeadlines.setAdapter(adapter);
    }
}