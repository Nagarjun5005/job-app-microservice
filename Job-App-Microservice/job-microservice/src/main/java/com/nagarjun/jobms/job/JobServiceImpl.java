package com.nagarjun.jobms.job;

import com.nagarjun.jobms.client.CompanyClient;
import com.nagarjun.jobms.client.ReviewClient;
import com.nagarjun.jobms.dto.JobDTO;
import com.nagarjun.jobms.exchange.Company;
import com.nagarjun.jobms.exchange.Review;
import com.nagarjun.jobms.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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

   private CompanyClient companyClient;
   private ReviewClient reviewClient;


    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository,CompanyClient companyClient,ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient=companyClient;
        this.reviewClient=reviewClient;
    }

    @Override
    @Retry(name="companyRetry",fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name="companyLimiter",fallbackMethod = "rateLimitFallback")
    @CircuitBreaker(name = "companyBreaker",fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAllJobs() {
        List<Job>jobList=jobRepository.findAll();
        return jobList.stream().map(this::convertToDTO).collect(Collectors.toList());


    }

    public List<String> companyBreakerFallback(Exception e){
        List<String>list=new ArrayList<>();
        list.add("dummy data");
        return list;
    }

    public String rateLimitFallback(Throwable t) {
        return "Too many requests - please try again later.";
    }

    private JobDTO convertToDTO(Job job){
       Company company=companyClient.getCompany(job.getCompanyId());

        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        JobDTO jobDTO= JobMapper.jobCompanyMapper(job,company,reviews);
        return jobDTO;

    }

    @Override
    public JobDTO findJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        JobDTO jobCompanyDTO = convertToDTO(job);
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
