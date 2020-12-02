package pl.coderslab.warjees26sb.seats;

import java.util.List;

public interface SeatService {

    void add(Seat seat);

    List<Seat> findAllSeats(Long flNum);

    void update(Seat seat);

    void deleteAll();
}
