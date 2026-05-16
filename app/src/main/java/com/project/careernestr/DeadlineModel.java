package com.project.careernestr;

public class DeadlineModel {
    private String jobTitle;
    private String companyName;
    private String date;
    private String daysLeft;
    private int sideColor; // বাম পাশের কালার স্ট্রাইপের জন্য

    public DeadlineModel(String jobTitle, String companyName, String date, String daysLeft, int sideColor) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.date = date;
        this.daysLeft = daysLeft;
        this.sideColor = sideColor;
    }

    // Getters
    public String getJobTitle() { return jobTitle; }
    public String getCompanyName() { return companyName; }
    public String getDate() { return date; }
    public String getDaysLeft() { return daysLeft; }
    public int getSideColor() { return sideColor; }
}