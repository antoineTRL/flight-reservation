package com.esiea.flightreservation.service;

import com.esiea.flightreservation.model.Airport;
import com.esiea.flightreservation.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public Airport getAirportById(UUID id) {
        return airportRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Airport not found"));
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport saveAirport(Airport airportRequest) {
        return airportRepository.save(airportRequest);
    }

    public Airport updateAirport(Airport airportRequest, UUID id) {
        Airport airport = getAirportById(id);
        airport.setName(airportRequest.getName());
        airport.setCity(airportRequest.getCity());
        airport.setCountry(airportRequest.getCountry());

        return airportRepository.save(airport);
    }

    public void deleteAirport(UUID id) {
        if (!airportRepository.existsById(id)) {
            throw new IllegalArgumentException("Airport not found");
        }
        airportRepository.deleteById(id);
    }

}
