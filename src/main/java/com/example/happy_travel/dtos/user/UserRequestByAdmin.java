package com.example.happy_travel.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestByAdmin(
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username must be less than 50 characters")
        String username,

        @NotBlank(message = "Email is required")
        @Email(message = "Email not valid", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        String email,

        @NotBlank(message = "Role is required")
        String role
) {
}
