package com.example.happy_travel.dtos.destination;

import com.example.happy_travel.dtos.user.UserResponse;

public record DestinationResponse(
        Long id,
        String title,
        String country,
        String city,
        String image,
        String description,
        UserResponse user
) {
}