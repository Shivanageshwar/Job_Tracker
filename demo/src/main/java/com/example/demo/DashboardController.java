package com.example.demo;

import com.example.demo.dto.DashboardResponse;
import com.example.demo.service.DashboardService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardResponse getDashboard(
            Authentication authentication) {

        String email = authentication.getName();

        return dashboardService.getDashboard(email);
    }
}