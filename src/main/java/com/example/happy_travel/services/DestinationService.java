package com.example.happy_travel.services;

import com.example.happy_travel.dtos.destination.DestinationMapper;
import com.example.happy_travel.dtos.destination.DestinationResponse;
import com.example.happy_travel.models.Destination;
import com.example.happy_travel.repositories.DestinationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DestinationService {
    public final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<DestinationResponse> getAllDestinations() {
        List<Destination> destinations = destinationRepository.findAll();
        return destinations.stream()
                .map(destination -> DestinationMapper.toDto(destination))
                .toList();
    }
}
