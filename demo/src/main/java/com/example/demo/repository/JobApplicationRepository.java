package com.example.demo.repository;

import com.example.demo.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByStatus(String status);

    List<JobApplication> findByCompanyName(String companyName);

    List<JobApplication> findByUserId(Long userId);

    List<JobApplication> findByUserIdAndStatus(
            Long userId,
            String status
    );

    List<JobApplication> findByUserIdAndCompanyName(
            Long userId,
            String companyName
    );

    long countByUserId(Long userId);

    long countByUserIdAndStatus(
            Long userId,
            String status
    );
}