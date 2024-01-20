package com.amadeus.repository;

import com.amadeus.repository.entity.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAirportRepository extends JpaRepository<Airports, Long> {

    Optional<Airports> findOptionalByAirportNameEqualsIgnoreCase(String airportName);
}
