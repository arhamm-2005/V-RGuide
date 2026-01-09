package com.example.V_RGUIDE.controller;

import com.example.V_RGUIDE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    // Endpoint for Admin to see the statistics
    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        return userService.getAdminStats();
    }

    // Endpoint for Admin to approve a counsellor
    @PutMapping("/approve/{email}")
    public String approve(@PathVariable String email) {
        return userService.approveCounsellor(email);
    }

    // Admin can delete any user (CRUD operation)
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        // You can implement userRepository.deleteById(id) in service
        return "User deleted successfully";
    }

    // AdminController.java
    @GetMapping("/report")
    public List<String> getFullReport() {
        return userService.getBookingReport();
    }
}