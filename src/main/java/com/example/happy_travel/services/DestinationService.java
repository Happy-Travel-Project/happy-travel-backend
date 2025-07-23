package com.example.happy_travel.services;

import java.util.NoSuchElementException;
import com.example.happy_travel.repositories.DestinationRepository;
import org.springframework.stereotype.Service;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;

    }

    public void deleteDestination(Long id){
        var destination = destinationRepository.findById(id);

        if (destination.isEmpty()){
            throw new NoSuchElementException("Destination wit ID" + id + "not found");
        }
    }
}
