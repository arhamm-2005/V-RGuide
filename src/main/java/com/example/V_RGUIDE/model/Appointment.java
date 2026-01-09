package com.example.V_RGUIDE.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "appointments")
public class Appointment {
    @Id
    private String id;
    private String studentEmail;
    private String counsellorEmail;
    private LocalDateTime startTime;
    private LocalDateTime endTime; // We will calculate this to be startTime + 1 hour

    // This ensures the appointment is always exactly 1 hour (Immutability logic)
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        this.endTime = startTime.plusHours(1); 
    }
}