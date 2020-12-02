package pl.coderslab.warjees26sb.seats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetSeatRepository extends JpaRepository<TargetSeat, Long> {

    List<TargetSeat> findTargetSeatsBySeat(Seat seat);
}
