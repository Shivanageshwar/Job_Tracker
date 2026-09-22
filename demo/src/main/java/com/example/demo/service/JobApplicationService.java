package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.JobApplication;
import com.example.demo.entity.User;
import com.example.demo.repository.JobApplicationRepository;
import com.example.demo.repository.UserRepository;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;
    private final UserRepository userRepository;

    public JobApplicationService(
            JobApplicationRepository repository,
            UserRepository userRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
    }

    // Get all applications
    public List<JobApplication> getAllApplications() {
        return repository.findAll();
    }

    // Create application
   public JobApplication createApplication(
        JobApplication application,
           String email) {

       User user = userRepository
               .findByEmail(email)
               .orElseThrow(() -> new RuntimeException("User not found"));

       application.setUser(user);

       return repository.save(application);
   }

public List<JobApplication> getAllApplications(
        String email) {

    User user = userRepository
            .findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found")
            );

    return repository.findByUserId(user.getId());
}

    // Get application by ID
    public JobApplication getApplicationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }

    // Update application
    public JobApplication updateApplication(Long id, JobApplication updatedApplication) {

        JobApplication existingApplication = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        existingApplication.setCompanyName(updatedApplication.getCompanyName());
        existingApplication.setJobRole(updatedApplication.getJobRole());
        existingApplication.setApplicationDate(updatedApplication.getApplicationDate());
        existingApplication.setStatus(updatedApplication.getStatus());
        existingApplication.setLocation(updatedApplication.getLocation());
        existingApplication.setJobType(updatedApplication.getJobType());
        existingApplication.setApplicationUrl(updatedApplication.getApplicationUrl());
        existingApplication.setNotes(updatedApplication.getNotes());

        return repository.save(existingApplication);
    }

    // Delete application
    public void deleteApplication(Long id) {
        repository.deleteById(id);
    }

    // Get applications by status
    public List<JobApplication> getApplicationsByStatus(
        String status,
        String email) {

    User user = userRepository
            .findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found")
            );

    return repository.findByUserIdAndStatus(
            user.getId(),
            status
    );
}

    // Get applications by company
    public List<JobApplication> getApplicationsByCompany(
        String companyName,
        String email) {

    User user = userRepository
            .findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found")
            );

    return repository.findByUserIdAndCompanyName(
            user.getId(),
            companyName
    );
}

}