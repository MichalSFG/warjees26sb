package pl.coderslab.warjees26sb.seats;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TargetSeatService {

    void add(TargetSeat targetSeat);

    void deleteAll();

    List<TargetSeat> findAllBySeat(Seat seat);
}
