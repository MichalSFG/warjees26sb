package pl.coderslab.warjees26sb.seats;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetSeatServiceImpl implements TargetSeatService {

    private final TargetSeatRepository targetSeatRepository;

    public TargetSeatServiceImpl(TargetSeatRepository targetSeatRepository) {
        this.targetSeatRepository = targetSeatRepository;
    }

    @Override
    public void add(TargetSeat targetSeat) {
        targetSeatRepository.save(targetSeat);
    }

    @Override
    public void deleteAll() {
        targetSeatRepository.deleteAll();
    }

    @Override
    public List<TargetSeat> findAllBySeat(Seat seat) {
        return targetSeatRepository.findTargetSeatsBySeat(seat);
    }

}
