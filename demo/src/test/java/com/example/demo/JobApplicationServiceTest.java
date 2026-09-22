package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.JobApplication;
import com.example.demo.entity.User;
import com.example.demo.repository.JobApplicationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JobApplicationService;

@ExtendWith(MockitoExtension.class)
class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository repository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JobApplicationService service;

    @Test
    void createApplication_shouldAttachUserAndSave() {
        User user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");

        JobApplication application = new JobApplication();
        application.setCompanyName("Acme");
        application.setJobRole("Java Developer");
        application.setStatus("Applied");

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(repository.save(any(JobApplication.class))).thenAnswer(invocation -> invocation.getArgument(0));

        JobApplication saved = service.createApplication(application, "user@example.com");

        assertNotNull(saved);
        assertEquals(user, saved.getUser());
    }

    @Test
    void getAllApplications_shouldReturnOnlyTheLoggedInUsersEntries() {
        User user = new User();
        user.setId(7L);
        user.setEmail("user@example.com");

        JobApplication app = new JobApplication();
        app.setCompanyName("Contoso");
        app.setStatus("Interview");

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(repository.findByUserId(7L)).thenReturn(List.of(app));

        List<JobApplication> result = service.getAllApplications("user@example.com");

        assertEquals(1, result.size());
        assertEquals("Contoso", result.get(0).getCompanyName());
    }

    @Test
    void createApplication_shouldFailWhenUserDoesNotExist() {
        when(userRepository.findByEmail("missing@example.com")).thenReturn(Optional.empty());

        JobApplication application = new JobApplication();
        application.setCompanyName("Acme");
        application.setJobRole("Java Developer");
        application.setStatus("Applied");

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.createApplication(application, "missing@example.com"));

        assertEquals("User not found", ex.getMessage());
    }
}
