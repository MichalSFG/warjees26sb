package pl.coderslab.warjees26sb.flights;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class FlightController {

    private final FlightService flightService;
    private Flight flight;
    private String paxSeat;

    private final Logger logger = (Logger) LoggerFactory.getLogger(FlightController.class);

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping(value = "/dto", method = RequestMethod.GET)
    public String getForm() {
        return "flight/homepage";
    }

    @RequestMapping(value = "/dto", method = RequestMethod.POST)
    public String getFlightDetails(@RequestParam long flightNumber, Model model) {
        logger.info("FR " + flightNumber);
        String api = "http://aviation-edge.com/v2/public/timetable?key=07cf31-be1c81&flight_iata=FR"
                + flightNumber + "&type=arrival";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FlightDto[]> responseFlights = restTemplate.getForEntity(api, FlightDto[].class);
        FlightDto[] flights = responseFlights.getBody();

        assert flights != null;
        String flNumber = flights[0].getFlightNumber();

        flight = createFlight(flNumber, flights);

        Optional<Flight> optionalFlight = flightService.get(Long.parseLong(flNumber));
        if (optionalFlight.isEmpty()) {
            flightService.add(flight);
        }

        model.addAttribute("flights", flights);
        return "flight/flightInfo";
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

    @PostMapping("/dto/seatNo")
    public String getPaxSeatNo(@RequestParam String seatNum, @RequestParam String seatLetter, Model model) {
        paxSeat = seatNum + seatLetter;
        model.addAttribute("seat", paxSeat);
        model.addAttribute("flight", flight);
        return "flight/seatInfo";
    }

    @PostMapping("/dto/seatsToSwap")
    public String getSeatsToSwap(@RequestParam List<String> seats, Model model) {
        model.addAttribute("seats", seats);
        model.addAttribute("seat", paxSeat);
        model.addAttribute("flight", flight);
        return "flight/finalInfo";
    }

    @RequestMapping("/dto/all")
    public String getAllFlights(Model model) {
        List<Flight> flights = flightService.getAllFlights();
        model.addAttribute("flights", flights);
        return "allFlights";
    }
}
