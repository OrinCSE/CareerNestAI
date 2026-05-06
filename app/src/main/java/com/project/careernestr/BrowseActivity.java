package com.project.careernestr;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BrowseActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText searchBar;
    JobAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        // 🔷 Initialize views
        recyclerView = findViewById(R.id.recyclerViewJobs);
        searchBar = findViewById(R.id.searchBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 🔷 Create job list
        List<JobModel> jobList = new ArrayList<>();
        jobList.add(new JobModel("Mobile App Developer Intern", "Pathao", "BDT 15,000/month"));
        jobList.add(new JobModel("Software Engineer Intern", "Brain Station 23", "BDT 10,000/month"));
        jobList.add(new JobModel("Frontend Developer Intern", "REVE Systems", "BDT 8,000/month"));

        // 🔥 IMPORTANT: DON'T create adapter again locally
        adapter = new JobAdapter(this, jobList);
        recyclerView.setAdapter(adapter);

        // 🔥 SEARCH FILTER (this was missing)
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString()); // 🔥 filter call
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}