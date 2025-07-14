package com.example.happy_travel.dtos.destination;

public record DestinationResponse(
        Long id,
        String country,
        String city,
        String image,
        String description
) {
}

