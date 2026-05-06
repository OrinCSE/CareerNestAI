package com.project.careernestr;

public class JobModel {

    String title, company, salary;

    public JobModel(String title, String company, String salary) {
        this.title = title;
        this.company = company;
        this.salary = salary;
    }

    public String getTitle() { return title; }
    public String getCompany() { return company; }
    public String getSalary() { return salary; }
}