package com.nagarjun.jobms.dto;

import com.nagarjun.jobms.exchange.Company;
import com.nagarjun.jobms.job.Job;

public class JobCompanyDTO {

    private Company company;
    private Job job;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
