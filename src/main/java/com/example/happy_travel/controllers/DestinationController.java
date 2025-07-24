package com.example.happy_travel.controllers;

import com.example.happy_travel.dtos.destination.DestinationRequest;
import com.example.happy_travel.dtos.destination.DestinationResponse;
import com.example.happy_travel.services.DestinationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("")
public class DestinationController {
    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/destinations")
    public ResponseEntity<List<DestinationResponse>> getAllDestinations() {
        List<DestinationResponse> destinations = destinationService.getAllDestinations();
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/destinations/{id}")
    public ResponseEntity<DestinationResponse> getDestinationById(@PathVariable Long id) {
        DestinationResponse destinationResponse = destinationService.getDestinationById(id);
        return new ResponseEntity<>(destinationResponse, HttpStatus.OK);
    }

    @GetMapping("/destinations/title/{title}")
    public ResponseEntity<DestinationResponse> getDestinationByTitle(@PathVariable String title) {
        DestinationResponse destinationResponse = destinationService.getDestinationByTitle(title);
        return new ResponseEntity<>(destinationResponse, HttpStatus.OK);
    }

    @PostMapping("/destinations")
    public ResponseEntity<DestinationResponse> addDestination(@Valid @RequestBody DestinationRequest destinationRequest) {
        DestinationResponse createdDestination = destinationService.addDestination(destinationRequest);
        return new ResponseEntity<>(createdDestination, HttpStatus.CREATED);
    }

    @PutMapping("/destinations/{id}")
    public ResponseEntity<DestinationResponse> updateDestination(@PathVariable Long id, @Valid @RequestBody DestinationRequest destinationRequest) {
        DestinationResponse updateDestination = destinationService.updateDestination(id, destinationRequest);
        return ResponseEntity.ok(updateDestination);
    }

    @DeleteMapping("/destinations/{id}")
    public ResponseEntity<Object> deleteDestination(@PathVariable Long id){
        destinationService.deleteDestination(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}