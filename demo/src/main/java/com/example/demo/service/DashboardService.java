package com.example.demo.service;

import com.example.demo.dto.DashboardResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.JobApplicationRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final JobApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    public DashboardService(
            JobApplicationRepository applicationRepository,
            UserRepository userRepository) {

        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
    }

    public DashboardResponse getDashboard(String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        Long userId = user.getId();

        long total =
                applicationRepository.countByUserId(userId);

        long applied =
                applicationRepository.countByUserIdAndStatus(
                        userId,
                        "Applied"
                );

        long interview =
                applicationRepository.countByUserIdAndStatus(
                        userId,
                        "Interview"
                );

        long selected =
                applicationRepository.countByUserIdAndStatus(
                        userId,
                        "Selected"
                );

        long rejected =
                applicationRepository.countByUserIdAndStatus(
                        userId,
                        "Rejected"
                );

        return new DashboardResponse(
                total,
                applied,
                interview,
                selected,
                rejected
        );
    }
}
