package com.amadeus.repository;

import com.amadeus.repository.entity.Flights;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
public interface IFlightRepository extends MongoRepository<Flights, String> {

    @Query(value = "{ 'departureTime': { '$gte': ?0, '$lt': ?1 }, 'departureAirport': ?2, 'arrivalAirport': ?3  }")
    List<Flights> searchByQuery(Instant startOfDay, Instant endOfDay, String departureAirport, String  arrivalAirport);


}
