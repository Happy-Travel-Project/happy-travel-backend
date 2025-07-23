package com.example.happy_travel.services;

import com.example.happy_travel.dtos.destination.DestinationMapper;
import com.example.happy_travel.dtos.destination.DestinationRequest;
import com.example.happy_travel.dtos.destination.DestinationResponse;
import com.example.happy_travel.dtos.user.UserMapper;
import com.example.happy_travel.dtos.user.UserRequest;
import com.example.happy_travel.dtos.user.UserResponse;
import com.example.happy_travel.exceptions.EntityAlreadyExistsException;
import com.example.happy_travel.models.Destination;
import com.example.happy_travel.models.User;
import com.example.happy_travel.repositories.DestinationRepository;
import org.springframework.stereotype.Service;

@Service
public class DestinationService {

    public final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository){
        this.destinationRepository = destinationRepository;
    }

    public DestinationResponse addDestination(DestinationRequest destinationRequest) {
        if (destinationRepository.existsByTitle(destinationRequest.title())){
            throw new EntityAlreadyExistsException(Destination.class.getSimpleName(), "title", destinationRequest.title());
        }
        Destination newDestination = DestinationMapper.toEntity(destinationRequest);
        Destination savedDestination = destinationRepository.save(newDestination);
        return DestinationMapper.toDto(savedDestination);
    }
}
