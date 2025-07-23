package com.example.happy_travel.services;

import com.example.happy_travel.dtos.destination.DestinationMapper;
import com.example.happy_travel.dtos.destination.DestinationRequest;
import com.example.happy_travel.dtos.destination.DestinationResponse;
import com.example.happy_travel.exceptions.EntityNotFoundException;
import com.example.happy_travel.models.Destination;
import java.util.NoSuchElementException;
import com.example.happy_travel.repositories.DestinationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DestinationService {
    public final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository){
        this.destinationRepository = destinationRepository;
    }

    public List<DestinationResponse> getAllDestinations() {
        List<Destination> destinations = destinationRepository.findAll();
        return destinations.stream()
                .map(destination -> DestinationMapper.toDto(destination))
                .toList();
    }
    public DestinationResponse updateDestination(Long id, DestinationRequest destinationRequest){
        Destination updateDestination = destinationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Destination.class.getSimpleName(), "id", id.toString()));

        updateDestination.setCountry(destinationRequest.country());
        updateDestination.setCity(destinationRequest.city());
        updateDestination.setImage(destinationRequest.image());
        updateDestination.setDescription(destinationRequest.description());
    public void deleteDestination(Long id){
        var destination = destinationRepository.findById(id);

        Destination newDestination = destinationRepository.save(updateDestination);

        return DestinationMapper.toDto(newDestination);
        if (destination.isEmpty()){
            throw new NoSuchElementException("Destination wit ID" + id + "not found");
        }
    }
}
