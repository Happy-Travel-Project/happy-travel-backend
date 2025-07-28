package com.example.happy_travel.dtos.destination;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DestinationRequest(
        @NotBlank(message = "Title must not be blank")
        @Size(min =5, max =100 , message = "Title must be between 5 and 100 characters")
        String title,

        @NotBlank(message = "Country must not be blank")
        @Size(min =2, max =50 , message = "Country must be between 2 and 50 characters")
        String country,

        @NotBlank(message = "City must not be blank")
        @Size(min =2, max =50 , message = "City must be between 2 and 50 characters")
        String city,

        @NotBlank(message = "Image must not be blank")
        @Pattern(message = "Invalid content type", regexp = "^(https?://.*\\.(png|jpg|jpeg|gif|svg))$")
        String image,

        @NotBlank(message = "Description must not be blank")
        @Size(min =5, message = "Description must be greater than 5 characters")
        String description
) {
}
