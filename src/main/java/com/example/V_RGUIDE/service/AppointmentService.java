package com.example.V_RGUIDE.service;

import com.example.V_RGUIDE.model.Appointment;
import com.example.V_RGUIDE.model.Counsellor;
import com.example.V_RGUIDE.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.V_RGUIDE.model.User;
import com.example.V_RGUIDE.repository.UserRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    // 'synchronized' prevents thread interference (Deadlock/Race Condition prevention)
    public synchronized String bookAppointment(Appointment appointment) {
        User counsellor = userRepository.findByEmail(appointment.getCounsellorEmail());
    
    // Safety check: Is the counsellor approved?
    if (counsellor instanceof Counsellor && !"APPROVED".equals(((Counsellor) counsellor).getStatus())) {
        return "Error: This counsellor is not yet approved by Admin!";
    }
        // Check if counsellor already has a booking at this time
        boolean exists = appointmentRepository.findByCounsellorEmail(appointment.getCounsellorEmail())
            .stream()
            .anyMatch(a -> a.getStartTime().equals(appointment.getStartTime()));

        if (exists) {
            return "Error: This slot is already booked!";
        }

        appointmentRepository.save(appointment);
        return "Appointment booked for 1 hour: " + appointment.getStartTime() + " to " + appointment.getEndTime();
    }
}