package com.example.V_RGUIDE.controller;

import com.example.V_RGUIDE.model.Counsellor;
import com.example.V_RGUIDE.model.Student;
import com.example.V_RGUIDE.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // Make sure this is here!
@RequestMapping("/api/users") // This is the base path
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register-student") // This matches the end of your URL
    public String registerStudent(@RequestBody Student student) {
        userService.registerUser(student);
        userService.sendWelcomeNotification(student.getEmail());
        return "Student registered successfully!";
    }
    // ADD THIS METHOD for the Counsellor
    @PostMapping("/register-counsellor")
    public String registerCounsellor(@RequestBody Counsellor counsellor) {
        // By default, they are PENDING
        counsellor.setStatus("PENDING"); 
        userService.registerUser(counsellor);
        return "Counsellor registration submitted! Waiting for Admin approval.";
    }
// New Endpoint to search for Counsellors by specialization
@GetMapping("/search-counsellors")
public List<Counsellor> search(@RequestParam String specialization) {
    return userService.findCounsellorsBySpecialization(specialization);
}
}