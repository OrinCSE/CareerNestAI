package com.project.careernestr;

import java.io.Serializable;

public class JobModel implements Serializable {
    private String job_title;
    private String company_name;
    private String location;
    private String salary;
    private String match_percentage;
    private String job_type;
    private String level;
    private String job_url;

    public JobModel() {}

    public JobModel(String job_title, String company_name, String location, String salary, String match_percentage, String job_type, String level) {
        this.job_title = job_title;
        this.company_name = company_name;
        this.location = location;
        this.salary = salary;
        this.match_percentage = match_percentage;
        this.job_type = job_type;
        this.level = level;
        this.job_url = "https://jobs.bdjobs.com";
    }

    public String getJobTitle() { return job_title != null ? job_title : "No Title"; }
    public String getTitle() { return getJobTitle(); }
    public void setJobTitle(String job_title) { this.job_title = job_title; }

    public String getCompanyName() { return company_name != null ? company_name : "Unknown Company"; }
    public String getCompany() { return getCompanyName(); }
    public void setCompanyName(String company_name) { this.company_name = company_name; }

    public String getLocation() { return location != null ? location : "Bangladesh"; }
    public void setLocation(String location) { this.location = location; }

    public String getSalary() { return salary != null ? salary : "Negotiable"; }
    public String getStipend() { return getSalary(); }
    public void setSalary(String salary) { this.salary = salary; }

    public String getMatchPercentage() { return match_percentage != null ? match_percentage : "0"; }
    public int getMatchPercentageAsInt() {
        try {
            return Integer.parseInt(getMatchPercentage().replace("%", "").trim());
        } catch (Exception e) {
            return 0;
        }
    }
    public int getMatchScore() { return getMatchPercentageAsInt(); }
    public void setMatchPercentage(String match_percentage) { this.match_percentage = match_percentage; }

    public String getJobType() { return job_type != null ? job_type : "onsite"; }
    public void setJobType(String job_type) { this.job_type = job_type; }

    public String getLevel() { return level != null ? level : "Beginner"; }
    public void setLevel(String level) { this.level = level; }

    public String getJobUrl() { return job_url != null ? job_url : "https://jobs.bdjobs.com"; }
    public String getApplyLink() { return getJobUrl(); }
    public void setJobUrl(String job_url) { this.job_url = job_url; }
}