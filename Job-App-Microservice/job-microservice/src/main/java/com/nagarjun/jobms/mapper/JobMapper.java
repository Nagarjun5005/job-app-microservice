package com.nagarjun.jobms.mapper;

import com.nagarjun.jobms.dto.JobDTO;
import com.nagarjun.jobms.exchange.Company;
import com.nagarjun.jobms.exchange.Review;
import com.nagarjun.jobms.job.Job;

import java.util.List;

public class JobMapper {

    public static JobDTO jobCompanyMapper(Job job, Company company, List<Review>reviews){
          JobDTO jobDTO =new JobDTO();
          jobDTO.setId(job.getId());
          jobDTO.setTitle(job.getTitle());
          jobDTO.setDescription(job.getDescription());
          jobDTO.setLocation(job.getLocation());
          jobDTO.setMaxSalary(job.getMaxSalary());
          jobDTO.setMinSalary(job.getMinSalary());
          jobDTO.setCompany(company);
          jobDTO.setReviews(reviews);
          return jobDTO;
    }
}
