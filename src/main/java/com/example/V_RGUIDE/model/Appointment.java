package com.example.V_RGUIDE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "appointments")
public class Appointment {
    
    @Id
    private String id;
    
    private String studentEmail;
    private String counsellorEmail;
    
    // Using String for these matches your Monday/09:00 AM JSON format perfectly
    private String appointmentDate; 
    private String timeSlot;
    
    // Professional status tracking
    private String status; // BOOKED, CANCELLED, COMPLETED
    
    // Audit timestamp to see when the record was actually created
    private LocalDateTime createdAt = LocalDateTime.now();

    // Custom helper if you ever need to convert to actual Date objects later
    public void setBookingDetails(String date, String slot) {
        this.appointmentDate = date;
        this.timeSlot = slot;
    }

    public String getEndTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEndTime'");
    }
}