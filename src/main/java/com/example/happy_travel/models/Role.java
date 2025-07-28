package com.example.happy_travel.models;

public enum Role {
    USER,
    ADMIN;

    public String getName() {
        return "ROLE_" + this.name();
    }
}
