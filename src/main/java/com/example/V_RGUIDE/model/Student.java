package com.example.V_RGUIDE.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends User {
    private String studentType; // "COLLEGE" or "UNIVERSITY"
    private String major;

    public Student() {
        this.setRole("STUDENT");
    }
}