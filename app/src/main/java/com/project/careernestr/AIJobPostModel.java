package com.project.careernestr; // তোমার প্রজেক্টের প্যাকেজ নাম ঠিক রেখো

public class AIJobPostModel {
    private String jobTitle, companyName, postedDate, matchExplanation, applicationUrl;
    private int matchPercentage;

    public AIJobPostModel(String jobTitle, String companyName, String postedDate, String matchExplanation, String applicationUrl, int matchPercentage) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.postedDate = postedDate;
        this.matchExplanation = matchExplanation;
        this.applicationUrl = applicationUrl;
        this.matchPercentage = matchPercentage;
    }

    public String getJobTitle() { return jobTitle; }
    public String getCompanyName() { return companyName; }
    public String getPostedDate() { return postedDate; }
    public String getMatchExplanation() { return matchExplanation; }
    public String getApplicationUrl() { return applicationUrl; }
    public int getMatchPercentage() { return matchPercentage; }
}