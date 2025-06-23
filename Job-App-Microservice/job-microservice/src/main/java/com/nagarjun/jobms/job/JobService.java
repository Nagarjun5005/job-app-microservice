package com.nagarjun.jobms.job;

import com.nagarjun.jobms.dto.JobCompanyDTO;

import java.util.List;

public interface JobService {

    List<JobCompanyDTO> findAllJobs();

    JobCompanyDTO findJobById(Long id);

    void createJob(Job job);

    boolean updateJob(Long id, Job job);

    boolean deleteJob(Long id);
}
