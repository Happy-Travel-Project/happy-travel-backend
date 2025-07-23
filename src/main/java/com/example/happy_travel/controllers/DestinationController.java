package com.example.happy_travel.controllers;

import com.example.happy_travel.dtos.destination.DestinationRequest;
import com.example.happy_travel.dtos.destination.DestinationResponse;
import com.example.happy_travel.services.DestinationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PutMapping("/destination/{id}")
    public ResponseEntity<DestinationResponse> updateDestination(@PathVariable Long id, @Valid @RequestBody DestinationRequest destinationRequest){
        DestinationResponse updateDestination = destinationService.updateDestination(id, destinationRequest);
        return ResponseEntity.ok(updateDestination);
    }
}
