package pl.coderslab.warjees26sb.seats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findSeatsByFlight_FlightNumber(Long flNum);


}
