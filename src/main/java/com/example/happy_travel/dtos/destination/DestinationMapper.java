package com.example.happy_travel.dtos.destination;

import com.example.happy_travel.dtos.user.UserMapper;
import com.example.happy_travel.dtos.user.UserResponse;
import com.example.happy_travel.models.Destination;
import com.example.happy_travel.models.User;

public class DestinationMapper {

    public static Destination toEntity(DestinationRequest request, User user) {
        return new Destination(
                request.title(),
                request.country(),
                request.city(),
                request.image(),
                request.description(),
                user
        );
    }

    public static DestinationResponse toDto(Destination destination) {
        UserResponse userResponse = destination.getUser() != null
                ? UserMapper.toDto(destination.getUser())
                : null;
        return new DestinationResponse(
                destination.getId(),
                destination.getTitle(),
                destination.getCountry(),
                destination.getCity(),
                destination.getImage(),
                destination.getDescription(),
                userResponse
        );
    }
}

