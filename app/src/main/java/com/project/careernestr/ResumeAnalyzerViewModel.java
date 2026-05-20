package com.project.careernestr;
import com.project.careernestr.AIJobPostModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ResumeAnalyzerViewModel extends AndroidViewModel {

    private final MutableLiveData<List<AIJobPostModel>> recommendedJobs = new MutableLiveData<>();

    public ResumeAnalyzerViewModel(@NonNull Application application) {
        super(application);
        loadRecommendedJobs();
    }

    public LiveData<List<AIJobPostModel>> getRecommendedJobs() {
        return recommendedJobs;
    }

    private void loadRecommendedJobs() {
        List<AIJobPostModel> mockJobs = new ArrayList<>();

        mockJobs.add(new AIJobPostModel(
                "Android App Developer (Kotlin)",
                "Pathao Ltd.",
                "Posted 2 days ago",
                "Matches because your resume contains: Java, Android Studio, REST API",
                "https://jobs.bdjobs.com",
                92
        ));

        mockJobs.add(new AIJobPostModel(
                "Junior Mobile Developer Intern",
                "Brain Station 23",
                "Posted Today",
                "Matches because your resume contains: Java, Firebase",
                "https://jobs.bdjobs.com",
                81
        ));

        mockJobs.add(new AIJobPostModel(
                "Software Engineer (Java Backend)",
                "TigerIT Bangladesh",
                "Posted 4 days ago",
                "Matches because your resume contains: Java, Git",
                "https://jobs.bdjobs.com",
                74
        ));

        recommendedJobs.setValue(mockJobs);
    }
}