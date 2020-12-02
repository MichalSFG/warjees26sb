package pl.coderslab.warjees26sb.flights;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FlightService {

    List<Flight> getAllFlights();

    void add(Flight flight);

    Optional<Flight> get(long flightNumber);

    Flight find(long flNum);

    void delete(long flightNumber);

    void update(Flight flight);

    void deleteAll();
}
