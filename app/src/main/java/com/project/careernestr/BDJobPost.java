package com.project.careernestr;

import java.util.List;

public class BDJobPost {
    private String jobId;
    private String jobTitle;
    private String companyName;
    private List<String> requiredSkills;
    private int minExperience;
    private String targetEducation;
    private String applicationUrl;
    private String postedDate;

    // Algorithm Output Fields
    private int matchPercentage;
    private String matchExplanation;

    public BDJobPost(String jobId, String jobTitle, String companyName, List<String> requiredSkills,
                     int minExperience, String targetEducation, String applicationUrl, String postedDate) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.requiredSkills = requiredSkills;
        this.minExperience = minExperience;
        this.targetEducation = targetEducation;
        this.applicationUrl = applicationUrl;
        this.postedDate = postedDate;
    }

    // Getters and Setters
    public String getJobTitle() { return jobTitle; }
    public String getCompanyName() { return companyName; }
    public List<String> getRequiredSkills() { return requiredSkills; }
    public int getMinExperience() { return minExperience; }
    public String getTargetEducation() { return targetEducation; }
    public String getApplicationUrl() { return applicationUrl; }
    public String getPostedDate() { return postedDate; }
    public int getMatchPercentage() { return matchPercentage; }
    public void setMatchPercentage(int matchPercentage) { this.matchPercentage = matchPercentage; }
    public String getMatchExplanation() { return matchExplanation; }
    public void setMatchExplanation(String matchExplanation) { this.matchExplanation = matchExplanation; }
}