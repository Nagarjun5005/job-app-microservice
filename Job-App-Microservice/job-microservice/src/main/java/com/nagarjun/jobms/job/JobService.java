package com.nagarjun.jobms.job;

import com.nagarjun.jobms.dto.JobDTO;

import java.util.List;

public interface JobService {

    List<JobDTO> findAllJobs();

    JobDTO findJobById(Long id);

    void createJob(Job job);

    boolean updateJob(Long id, Job job);

    boolean deleteJob(Long id);
}
