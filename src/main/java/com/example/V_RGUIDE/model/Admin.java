package com.example.V_RGUIDE.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin extends User {
    
    public Admin() {
        this.setRole("ADMIN");
    }
    // You can add admin-specific fields here later, like "adminLevel"
}