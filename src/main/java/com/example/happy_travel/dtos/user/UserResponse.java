package com.example.happy_travel.dtos.user;

public record UserResponse(
        Long id,
        String username,
        String email,
        String role
) {
}