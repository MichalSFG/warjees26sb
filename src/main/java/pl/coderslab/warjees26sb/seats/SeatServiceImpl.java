package pl.coderslab.warjees26sb.seats;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public void add(Seat seat) {
        seatRepository.save(seat);
    }

    @Override
    public List<Seat> findAllSeats(Long flNum) {
        return seatRepository.findSeatsByFlight_FlightNumber(flNum);
    }

    @Override
    public void update(Seat seat) {
        seatRepository.save(seat);
    }

    @Override
    public void deleteAll() {
        seatRepository.deleteAll();
    }
}
