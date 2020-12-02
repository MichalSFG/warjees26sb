package pl.coderslab.warjees26sb.home;


import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.warjees26sb.flights.FlightController;
import pl.coderslab.warjees26sb.users.AppUser;
import pl.coderslab.warjees26sb.users.CurrentUser;

@Controller
public class LoginController {

    private final Logger logger = (Logger) LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        try {
            AppUser appUser = currentUser.getAppUser();
            if (appUser.getEnabled() == 0)
                model.addAttribute("access", "Admin denied your access!!!");
        } catch (NullPointerException e) {
            logger.info("access allowed");
        }
        return "loginPage";
    }

}
