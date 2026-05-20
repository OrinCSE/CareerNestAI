package com.project.careernestr;

import java.util.List;

public class ResumeProfile {
    private String primaryRole;
    private List<String> skills;
    private int experienceYears;
    private String educationLevel;

    public ResumeProfile(String primaryRole, List<String> skills, int experienceYears, String educationLevel) {
        this.primaryRole = primaryRole;
        this.skills = skills;
        this.experienceYears = experienceYears;
        this.educationLevel = educationLevel;
    }

    public String getPrimaryRole() { return primaryRole; }
    public List<String> getSkills() { return skills; }
    public int getExperienceYears() { return experienceYears; }
    public String getEducationLevel() { return educationLevel; }
}