package pl.coderslab.warjees26sb.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.warjees26sb.flights.FlightService;
import pl.coderslab.warjees26sb.users.CurrentUser;

@Controller
@Slf4j
public class HomeController {

    private final FlightService flightService;

    public HomeController(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping("/")
    public String home(@AuthenticationPrincipal CurrentUser customUser) {
        if (customUser != null) {
            log.info("Cześć {}", customUser.getAppUser().getFirstName());
            return "redirect:flight/home";
        } else
            log.info("Jesteś niezalogowany");
        return "home";
    }

    @RequestMapping("/theme")
    public String theme(){
        log.info("asdasd {}", 12);
        return "index";
    }

    @RequestMapping("/deleteFlights")
    @ResponseBody
    public String deleteFlights() {
        flightService.deleteAll();
        return "deleted";
    }

}
