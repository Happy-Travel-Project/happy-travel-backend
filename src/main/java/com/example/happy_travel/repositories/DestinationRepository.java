package com.example.happy_travel.repositories;

import java.util.Optional;
import com.example.happy_travel.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Optional<Destination> findByTitle(String title);
    Boolean existsByTitle(String title);
}
