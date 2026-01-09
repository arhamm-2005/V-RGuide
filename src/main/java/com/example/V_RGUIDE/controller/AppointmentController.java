package com.example.V_RGUIDE.controller;

import com.example.V_RGUIDE.model.Appointment;
import com.example.V_RGUIDE.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Endpoint to book a session
    @PostMapping("/book")
    public String book(@RequestBody Appointment appointment) {
        // This triggers the synchronized logic to prevent double-booking
        return appointmentService.bookAppointment(appointment);
    }
}