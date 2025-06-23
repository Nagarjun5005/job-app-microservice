package com.nagarjun.jobms.mapper;

import com.nagarjun.jobms.dto.JobCompanyDTO;
import com.nagarjun.jobms.exchange.Company;
import com.nagarjun.jobms.job.Job;

public class JobCompanyMapper {

    public static JobCompanyDTO jobCompanyMapper(Job job, Company company){
          JobCompanyDTO jobCompanyDTO=new JobCompanyDTO();
          jobCompanyDTO.setId(job.getId());
          jobCompanyDTO.setTitle(job.getTitle());
          jobCompanyDTO.setDescription(job.getDescription());
          jobCompanyDTO.setLocation(job.getLocation());
          jobCompanyDTO.setMaxSalary(job.getMaxSalary());
          jobCompanyDTO.setMinSalary(job.getMinSalary());
          jobCompanyDTO.setCompany(company);
          return jobCompanyDTO;
    }
}
