package pl.coderslab.warjees26sb.flights;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime scheduledTimeOfDept;
    private LocalDateTime scheduledTimeOfArr;
    private LocalDateTime created;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

}
