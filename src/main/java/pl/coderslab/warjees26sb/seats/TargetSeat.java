package pl.coderslab.warjees26sb.seats;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "targetSeats")
@Data
public class TargetSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String targetSeatNo;

    @ManyToOne
    private Seat seat;

}
