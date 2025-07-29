package com.example.happy_travel.repositories;

import java.util.Optional;
import com.example.happy_travel.models.Destination;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Optional<Destination> findByTitle(String title);
    List<Destination> findByUserId(Long userId);
    List<Destination> findByCityIgnoreCase(String city);
    List<Destination> findByCountryIgnoreCase(String country);
    Boolean existsByTitle(String title);
}