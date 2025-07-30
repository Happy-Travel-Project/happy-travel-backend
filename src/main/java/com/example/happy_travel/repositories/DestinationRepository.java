package com.example.happy_travel.repositories;

import com.example.happy_travel.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Optional<Destination> findByTitle(String title);

    List<Destination> findByUserId(Long userId);

    List<Destination> findByCityIgnoreCase(String city);

    List<Destination> findByCountryIgnoreCase(String country);

    Boolean existsByTitle(String title);
}