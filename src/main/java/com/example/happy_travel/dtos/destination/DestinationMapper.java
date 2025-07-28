package com.example.happy_travel.dtos.destination;

import com.example.happy_travel.dtos.user.UserMapper;
import com.example.happy_travel.models.Destination;

public class DestinationMapper {
    public static Destination toEntity(DestinationRequest destinationRequest){
        return new Destination(destinationRequest.title(), destinationRequest.country(), destinationRequest.city(), destinationRequest.image(), destinationRequest.description());
    }

    public static DestinationResponse toDto(Destination destination){
        return new DestinationResponse(destination.getId(), destination.getTitle(), destination.getCountry(), destination.getCity(), destination.getImage(), destination.getDescription(), UserMapper.toDto(destination.getUser()));
    }
}