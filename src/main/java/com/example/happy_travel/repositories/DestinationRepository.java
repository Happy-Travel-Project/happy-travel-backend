package com.example.happy_travel.repositories;

import com.example.happy_travel.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
