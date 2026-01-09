package com.example.V_RGUIDE.service;

import com.example.V_RGUIDE.model.Counsellor;
import com.example.V_RGUIDE.model.Student;
import com.example.V_RGUIDE.model.User;
import com.example.V_RGUIDE.repository.UserRepository;
import com.example.V_RGUIDE.repository.AppointmentRepository;
import com.example.V_RGUIDE.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    // Standard Logic: Saving a user
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Concurrency: This runs on a SEPARATE thread!
    @Async
    public void sendWelcomeNotification(String email) {
        System.out.println(
                "Threading: Sending notification to " + email + " on thread: " + Thread.currentThread().getName());
        // In a real app, this is where your email sending logic would go
    }

    public User findUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with email " + email + " not found!");
        }
        return user;
    }

    // Admin Method: Approve a Counsellor
    public String approveCounsellor(String email) {
        User user = userRepository.findByEmail(email);
        if (user instanceof Counsellor) {
            ((Counsellor) user).setStatus("APPROVED");
            userRepository.save(user);

            // This is where you trigger the Threaded Notification!
            sendWelcomeNotification(email);
            return "Counsellor approved and notified.";
        }
        throw new UserNotFoundException("Counsellor not found.");
    }

    public Map<String, Long> getAdminStats() {
        List<User> allUsers = userRepository.findAll();

        // Student counts
        long collegeStudents = allUsers.stream()
                .filter(u -> u instanceof Student && "COLLEGE".equalsIgnoreCase(((Student) u).getStudentType()))
                .count();

        long universityStudents = allUsers.stream()
                .filter(u -> u instanceof Student && "UNIVERSITY".equalsIgnoreCase(((Student) u).getStudentType()))
                .count();

        // Counsellor counts by Specialization
        long mentalHealthCounsellors = allUsers.stream()
                .filter(u -> u instanceof Counsellor
                        && "Mental Health".equalsIgnoreCase(((Counsellor) u).getSpecialization()))
                .count();

        long careerCounsellors = allUsers.stream()
                .filter(u -> u instanceof Counsellor
                        && "Career Guidance".equalsIgnoreCase(((Counsellor) u).getSpecialization()))
                .count();

        // Putting it all in the Map (Proper Format)
        Map<String, Long> stats = new HashMap<>();
        stats.put("CollegeStudents", collegeStudents);
        stats.put("UniversityStudents", universityStudents);
        stats.put("MentalHealthCounsellors", mentalHealthCounsellors);
        stats.put("CareerCounsellors", careerCounsellors);

        return stats;
    }

    // Method to find specific counsellors (Demonstrates Filter Logic)
    public List<Counsellor> findCounsellorsBySpecialization(String specialization) {
        return userRepository.findAll().stream()
                .filter(u -> u instanceof Counsellor)
                .map(u -> (Counsellor) u)
                .filter(c -> "APPROVED".equals(c.getStatus())) // Only show approved ones
                .filter(c -> c.getSpecialization().equalsIgnoreCase(specialization))
                .toList();
    }

    // UserService.java
    public List<String> getBookingReport() {
        // This demonstrates joining information from different sources (ADTs)
        return appointmentRepository.findAll().stream()
                .map(app -> "Student [" + app.getStudentEmail() + "] has a session with ["
                        + app.getCounsellorEmail() + "] at " + app.getStartTime())
                .toList();
    }
}