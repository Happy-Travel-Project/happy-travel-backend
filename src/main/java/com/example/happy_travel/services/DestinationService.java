package com.example.happy_travel.services;

import com.example.happy_travel.dtos.destination.DestinationMapper;
import com.example.happy_travel.dtos.destination.DestinationRequest;
import com.example.happy_travel.dtos.destination.DestinationResponse;
import com.example.happy_travel.exceptions.EntityNotFoundException;
import com.example.happy_travel.exceptions.EntityAlreadyExistsException;
import com.example.happy_travel.models.Destination;
import com.example.happy_travel.models.User;
import com.example.happy_travel.repositories.DestinationRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationService {

    public final DestinationRepository destinationRepository;

    public final UserService userService;

    public DestinationService(DestinationRepository destinationRepository, UserService userService) {
        this.destinationRepository = destinationRepository;
        this.userService = userService;
    }

    public List<DestinationResponse> getAllDestinations() {
        List<Destination> destinations = destinationRepository.findAll();
        return destinations.stream()
                .map(destination -> DestinationMapper.toDto(destination))
                .toList();
    }

    public DestinationResponse getDestinationById(Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Destination.class.getSimpleName(), "id", id.toString()));
        return DestinationMapper.toDto(destination);
    }

    public DestinationResponse getDestinationByTitle(String title) {
        Destination destination = destinationRepository.findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException(Destination.class.getSimpleName(), "title", title));
        return DestinationMapper.toDto(destination);
    }

    public List<DestinationResponse> getDestinationByCity(String city) {
        List<Destination> destinations = destinationRepository.findByCityIgnoreCase(city);
        if (destinations.isEmpty()) {
            throw new EntityNotFoundException(Destination.class.getSimpleName(), "city", city);
        }
        return destinations.stream()
                .map(destination -> DestinationMapper.toDto(destination))
                .collect(Collectors.toList());
    }

    public List<DestinationResponse> getDestinationByCountry(String country) {
        List<Destination> destinations = destinationRepository.findByCountryIgnoreCase(country);
        if (destinations.isEmpty()) {
            throw new EntityNotFoundException(Destination.class.getSimpleName(), "country", country);
        }
        return destinations.stream()
                .map(destination -> DestinationMapper.toDto(destination))
                .collect(Collectors.toList());
    }

    @PreAuthorize("isAuthenticated()")
    public DestinationResponse addDestination(DestinationRequest destinationRequest, User user) {
        if (destinationRepository.existsByTitle(destinationRequest.title())) {
            throw new EntityAlreadyExistsException(Destination.class.getSimpleName(), "title", destinationRequest.title());
        }
        Destination newDestination = DestinationMapper.toEntity(destinationRequest, user);
        Destination savedDestination = destinationRepository.save(newDestination);
        return DestinationMapper.toDto(savedDestination);
    }

    @PreAuthorize("isAuthenticated()")
    public DestinationResponse updateDestination(Long id, DestinationRequest destinationRequest, User user) {
        Destination updateDestination = destinationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Destination.class.getSimpleName(), "id", id.toString()));
        if (!updateDestination.getUser().getId().equals(user.getId())) {
            throw new SecurityException("You are not authorized to update this destination.");
        }
        updateDestination.setTitle(destinationRequest.title());
        updateDestination.setCountry(destinationRequest.country());
        updateDestination.setCity(destinationRequest.city());
        updateDestination.setImage(destinationRequest.image());
        updateDestination.setDescription(destinationRequest.description());
        Destination newDestination = destinationRepository.save(updateDestination);

        return DestinationMapper.toDto(newDestination);
    }
//REVIEW
    @PreAuthorize("isAuthenticated()")
    public void deleteDestination(Long id) {
        if (!destinationRepository.existsById(id)) {
            throw new EntityNotFoundException(Destination.class.getSimpleName(), "id", id.toString());
        }
        destinationRepository.deleteById(id);
    }
}
