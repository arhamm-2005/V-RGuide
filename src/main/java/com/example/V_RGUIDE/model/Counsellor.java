package com.example.V_RGUIDE.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Counsellor extends User {
    private String specialization; // e.g., "Career", "Mental Health"
    private List<String> availableSlots;
    private String status = "PENDING";

    public Counsellor() {
        this.setRole("COUNSELLOR");
    }
}