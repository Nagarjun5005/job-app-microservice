package com.nagarjun.jobms.job;

import com.nagarjun.jobms.dto.JobCompanyDTO;
import com.nagarjun.jobms.exchange.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class JobServiceImpl implements JobService {

    @Autowired
    RestTemplate restTemplate;


    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobCompanyDTO> findAllJobs() {
        List<Job>jobList=jobRepository.findAll();
        return jobList.stream().map(this::convertToDTO).collect(Collectors.toList());


    }

    private JobCompanyDTO convertToDTO(Job job){
        JobCompanyDTO jobCompanyDTO=new JobCompanyDTO();
        jobCompanyDTO.setJob(job);
        Company company= restTemplate.getForObject("http://COMPANY-SERVICE:8081/api/companies/"+job.getCompanyId(), Company.class);
        jobCompanyDTO.setCompany(company);
        return jobCompanyDTO;

    }

    @Override
    public JobCompanyDTO findJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        JobCompanyDTO jobCompanyDTO = convertToDTO(job);
        return jobCompanyDTO;

    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);

    }

    @Override
    public boolean updateJob(Long id, Job updateJob) {
        Optional<Job> existingJob = jobRepository.findById(id);

        if (existingJob.isPresent()) {
            Job found = existingJob.get();
            found.setDescription(updateJob.getDescription());
            found.setTitle(updateJob.getTitle());
            found.setMaxSalary(updateJob.getMaxSalary());
            found.setMinSalary(updateJob.getMinSalary());
            found.setLocation(updateJob.getLocation());
            jobRepository.save(found);
            return true;
        }


        return false;
    }

    @Override
    public boolean deleteJob(Long id) {
        Optional<Job> delete = jobRepository.findById(id);
        if (delete.isPresent()) {
            Job job = delete.get();
            jobRepository.delete(job);
            return true;
        }

        return false;
    }
}
