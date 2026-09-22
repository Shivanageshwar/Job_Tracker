package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.JobApplication;
import org.springframework.security.core.Authentication;
import com.example.demo.service.JobApplicationService;

import jakarta.validation.Valid;

@RestController
public class ApplicationController {

    private final JobApplicationService service;

    public ApplicationController(JobApplicationService service) {
        this.service = service;
    }

   @GetMapping("/applications")
public List<JobApplication> getApplications(
        Authentication authentication) {

    String email = authentication.getName();

    return service.getAllApplications(email);
}

  @PostMapping("/applications")
public JobApplication createApplication(
        @Valid @RequestBody JobApplication application,
        Authentication authentication) {

    String email = authentication.getName();

    return service.createApplication(
            application,
            email
    );
}
    @GetMapping("/applications/{id}")
    public JobApplication getApplicationById(@PathVariable Long id) {
        return service.getApplicationById(id);
}
@PutMapping("/applications/{id}")
public JobApplication updateApplication(
        @PathVariable Long id,
        @Valid @RequestBody JobApplication application) {

    return service.updateApplication(id, application);
}


@DeleteMapping("/applications/{id}")
public void deleteApplication(@PathVariable Long id) {
    service.deleteApplication(id);
}
@GetMapping("/applications/status/{status}")
public List<JobApplication> getApplicationsByStatus(
        @PathVariable String status,
        Authentication authentication) {

    String email = authentication.getName();

    return service.getApplicationsByStatus(
            status,
            email
    );
}

@GetMapping("/applications/company/{companyName}")
public List<JobApplication> getApplicationsByCompany(
        @PathVariable String companyName,
        Authentication authentication) {

    String email = authentication.getName();

    return service.getApplicationsByCompany(
            companyName,
            email
    );
}
}