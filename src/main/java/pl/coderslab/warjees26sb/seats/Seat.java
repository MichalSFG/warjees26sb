package pl.coderslab.warjees26sb.seats;

import lombok.Data;
import pl.coderslab.warjees26sb.flights.Flight;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seats")
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    @ManyToOne
    private Flight flight;

    @OneToMany(mappedBy = "seat", fetch = FetchType.EAGER)
    private List<TargetSeat> targetSeat = new ArrayList<>();

}
