package pl.coderslab.warjees26sb.flights;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.warjees26sb.seats.Seat;
import pl.coderslab.warjees26sb.seats.SeatService;
import pl.coderslab.warjees26sb.seats.TargetSeat;
import pl.coderslab.warjees26sb.seats.TargetSeatService;
import pl.coderslab.warjees26sb.users.AppUser;
import pl.coderslab.warjees26sb.users.CurrentUser;
import pl.coderslab.warjees26sb.users.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;
    private final SeatService seatService;
    private final UserService userService;
    private final TargetSeatService targetSeatService;

    private Flight flight;
    private Seat seat;

    private final Logger logger = (Logger) LoggerFactory.getLogger(FlightController.class);

    public FlightController(FlightService flightService, SeatService seatService, UserService userService, TargetSeatService targetSeatService) {
        this.flightService = flightService;
        this.seatService = seatService;
        this.userService = userService;
        this.targetSeatService = targetSeatService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getForm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", currentUser.getAppUser());
        return "flight/homepage";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String getFlightDetails(@RequestParam long flightNumber, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        logger.info("FR " + flightNumber);
        String api = "http://aviation-edge.com/v2/public/timetable?key=07cf31-be1c81&flight_iata=FR"
                + flightNumber + "&type=arrival";

        FlightDto[] flights;
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<FlightDto[]> responseFlights = restTemplate.getForEntity(api, FlightDto[].class);
            flights = responseFlights.getBody();
        } catch (RestClientException e) {
            logger.info("restClientEx");
            model.addAttribute("noFlight", "This flight is not available yet. Try later!");
            return "flight/homepage";
        }

        assert flights != null;
        String flNumber = flights[0].getFlightNumber();

        flight = createFlight(flNumber, flights);

        Optional<Flight> optionalFlight = flightService.get(Long.parseLong(flNumber));
        if (optionalFlight.isEmpty()) {
            flightService.add(flight);
        }

        model.addAttribute("user", currentUser.getAppUser());
        model.addAttribute("flight", flight);
        return "flight/flightInfo2";
    }

    private Flight createFlight(String number, FlightDto[] flights) {
        Flight flight = new Flight();
        flight.setFlightNumber(Long.parseLong(number));
        flight.setDepartureAirport(flights[0].getDeptAirport());
        flight.setArrivalAirport(flights[0].getArrAirport());
        flight.setScheduledTimeOfDept(LocalDateTime.parse(flights[0].getDeptTime()));
        flight.setScheduledTimeOfArr(LocalDateTime.parse(flights[0].getArrTime()));
        flight.prePersist();
        return flight;
    }

    @PostMapping("/seatNo")
    public String getPaxSeatNo(@RequestParam String seatNum, @RequestParam String seatLetter,
                               Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        Flight fl = flightService.find(flight.getFlightNumber());
        String paxSeat = seatNum + seatLetter;
        Seat seat = new Seat();
        seat.setSeatNumber(paxSeat);
        seat.setFlight(fl);
        seatService.add(seat);
        AppUser user = currentUser.getAppUser();
        user.setSeat(seat);
        userService.updateUser(user);

        model.addAttribute("user", currentUser.getAppUser());
        model.addAttribute("seat", paxSeat);
        model.addAttribute("flight", fl);
        return "flight/seatInfo2";
    }

    @PostMapping("/targetSeats")
    public String getSeatsToSwap(@RequestParam List<String> seats, Model model,
                                 @AuthenticationPrincipal CurrentUser currentUser) {
        List<TargetSeat> targetSeats = new ArrayList<>();

        seat = currentUser.getAppUser().getSeat();

        seats.forEach(s -> {
            TargetSeat targetSeat = new TargetSeat();
            targetSeat.setTargetSeatNo(s);
            targetSeat.setSeat(seat);
            targetSeatService.add(targetSeat);
            targetSeats.add(targetSeat);
        });

        seat.setTargetSeat(targetSeats);
        seatService.update(seat);

        model.addAttribute("seat", seat.getSeatNumber());
        model.addAttribute("seats", String.join(", ", seats));
        model.addAttribute("flight", flight);
        model.addAttribute("user", currentUser.getAppUser());
        return "flight/finalInfo2";
    }

    @RequestMapping("/results")
    private String showResults(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        Flight currentUserFlight = currentUser.getAppUser().getSeat().getFlight();
        Seat currentUserSeat = currentUser.getAppUser().getSeat();

        List<Seat> allSeatsOnFlight;
        List<TargetSeat> targetSeatsBySeat;
        List<Seat> yourTargetSeats = null;

        if (LocalDateTime.now().isAfter(currentUserFlight.getScheduledTimeOfDept().minusMinutes(10))) {
            allSeatsOnFlight = seatService.findAllSeats(currentUserFlight.getFlightNumber());
            allSeatsOnFlight.forEach(s -> logger.info("allSeatsOnFlight: " + s.getSeatNumber()));
            targetSeatsBySeat = targetSeatService.findAllBySeat(currentUserSeat);
            targetSeatsBySeat.forEach(s -> logger.info("targetSeatsBySeat " + currentUserSeat.getSeatNumber() + ": " + s.getTargetSeatNo()));
            yourTargetSeats = findTargetSeats(allSeatsOnFlight, targetSeatsBySeat, currentUserSeat);
        }

        String message;
        if (LocalDateTime.now().isAfter((currentUserFlight.getScheduledTimeOfDept().plusMinutes(20)))) {
            assert yourTargetSeats != null;
            if (!yourTargetSeats.isEmpty()) {
                message = "A passenger sitting in the above seat wants your seat big time! Swap and enjoy sitting with your people without extra costs :)";
                yourTargetSeats.forEach(s -> logger.info("seat: " + s.getSeatNumber()));
                model.addAttribute("seats", yourTargetSeats);
            } else {
                logger.info("You can't swap today. Sorry!");
                message = "nie da się dziś zamienić!";
            }
        } else
            message = "No results yet! Be patient!";

        model.addAttribute("message", message);
        model.addAttribute("flight", currentUserFlight);
        model.addAttribute("user", currentUser.getAppUser());
        return "flight/results2";
    }

    private List<Seat> findTargetSeats(List<Seat> allSeatsOnFlight, List<TargetSeat> targetSeatsBySeat, Seat seat) {
        List<String> list = targetSeatsBySeat.stream()
                .map(TargetSeat::getTargetSeatNo)
                .collect(Collectors.toList());

        List<Seat> seats = new ArrayList<>();

        for (Seat s : allSeatsOnFlight) {
            if (list.contains(s.getSeatNumber())) {
                List<String> collect = targetSeatService.findAllBySeat(s).stream()
                        .map(TargetSeat::getTargetSeatNo)
                        .collect(Collectors.toList());
                logger.info("seats to swap of " + s.getSeatNumber() + ": " + collect);
                if (collect.contains(seat.getSeatNumber())) {
                    seats.add(s);
                }
            }
        }
        return seats;
    }

    @RequestMapping("/all")
    public String getAllFlights(Model model) {
        List<Flight> flights = flightService.getAllFlights();
        model.addAttribute("flights", flights);
        return "allFlights";
    }
}