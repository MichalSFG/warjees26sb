package pl.coderslab.warjees26sb;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.coderslab.warjees26sb.flights.FlightService;
import pl.coderslab.warjees26sb.seats.SeatService;
import pl.coderslab.warjees26sb.seats.TargetSeatService;
import pl.coderslab.warjees26sb.users.UserService;

@Component
public class ScheduledTasks {

    private final UserService userService;
    private final TargetSeatService targetSeatService;
    private final SeatService seatService;
    private final FlightService flightService;

    private final Logger logger = (Logger) LoggerFactory.getLogger(ScheduledTasks.class);

    public ScheduledTasks(UserService userService, TargetSeatService targetSeatService, SeatService seatService, FlightService flightService) {
        this.userService = userService;
        this.targetSeatService = targetSeatService;
        this.seatService = seatService;
        this.flightService = flightService;
    }

    @Scheduled(cron = "29 59 23 * * *")
    public void resetEachUserSeat() {
        userService.updateUsers();
        logger.info("1st task - each user seat reset");
    }

    @Scheduled(cron = "39 59 23 * * *")
    public void deleteAllTargetSeats() {
        targetSeatService.deleteAll();
        logger.info("2nd task - all target seats deleted");
    }

    @Scheduled(cron = "49 59 23 * * *")
    public void deleteAllSeats() {
        seatService.deleteAll();
        logger.info("3rd task - all seats deleted");
    }

    @Scheduled(cron = "59 59 23 * * *")
    public void deleteAllFlights() {
        flightService.deleteAll();
        logger.info("4th task - all flights deleted");
    }

}
