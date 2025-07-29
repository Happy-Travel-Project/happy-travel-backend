package com.example.happy_travel.controllers;

import com.example.happy_travel.dtos.destination.DestinationRequest;
import com.example.happy_travel.dtos.destination.DestinationResponse;
import com.example.happy_travel.security.CustomUserDetail;
import com.example.happy_travel.services.DestinationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/destinations/user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<DestinationResponse>> getDestinationsOfUser(@AuthenticationPrincipal CustomUserDetail userDetail) {
        List<DestinationResponse> destinations = destinationService.getDestinationsOfUser(userDetail.getUser().getId());
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/destinations/title/{title}")
    public ResponseEntity<DestinationResponse> getDestinationByTitle(@PathVariable String title) {
        DestinationResponse destinationResponse = destinationService.getDestinationByTitle(title);
        return new ResponseEntity<>(destinationResponse, HttpStatus.OK);
    }

    @GetMapping("/destinations/city/{city}")
    public ResponseEntity<List<DestinationResponse>> getDestinationByCity(@PathVariable String city) {
        List<DestinationResponse> destinationResponse = destinationService.getDestinationByCity(city);
        return new ResponseEntity<>(destinationResponse, HttpStatus.OK);
    }

    @GetMapping("/destinations/country/{country}")
    public ResponseEntity<List<DestinationResponse>> getDestinationByCountry(@PathVariable String country) {
        List<DestinationResponse> destinationResponse = destinationService.getDestinationByCountry(country);
        return new ResponseEntity<>(destinationResponse, HttpStatus.OK);
    }

    @PostMapping("/destinations")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DestinationResponse> addDestination(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody @Valid DestinationRequest destinationRequest) {
        DestinationResponse createdDestination = destinationService.addDestination(destinationRequest, userDetail.getUser());
        return new ResponseEntity<>(createdDestination, HttpStatus.CREATED);
    }

    @PutMapping("/destinations/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DestinationResponse> updateDestination(@PathVariable Long id, @Valid @RequestBody DestinationRequest destinationRequest, @AuthenticationPrincipal CustomUserDetail userDetail) {
        DestinationResponse updateDestination = destinationService.updateDestination(id, destinationRequest, userDetail.getUser());
        return ResponseEntity.ok(updateDestination);
    }

    @DeleteMapping("/destinations/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> deleteDestination(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetail userDetail) {
        destinationService.deleteDestination(id, userDetail.getUser());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}