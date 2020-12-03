package pl.coderslab.warjees26sb.home;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.warjees26sb.seats.Seat;
import pl.coderslab.warjees26sb.seats.SeatService;
import pl.coderslab.warjees26sb.users.AppUser;
import pl.coderslab.warjees26sb.users.CurrentUser;
import pl.coderslab.warjees26sb.users.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final SeatService seatService;

    private final Logger logger = (Logger) LoggerFactory.getLogger(AdminController.class);

    public AdminController(UserService userService, SeatService seatService) {
        this.userService = userService;
        this.seatService = seatService;
    }

    @GetMapping("/dashboard")
    public String admin(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        List<AppUser> allUsers = userService.findAll();
        model.addAttribute("user", currentUser.getAppUser());
        model.addAttribute("allUsers", allUsers);
        return "adminPage";
    }

    @RequestMapping("/changeAccess/{id}")
    public String denyUser(@PathVariable Long id) {
        Optional<AppUser> byId = userService.findById(id);
        byId.ifPresent(appUser -> {
            int enabled = appUser.getEnabled();
            if (enabled == 1) {
                appUser.setEnabled(0);
            } else if (enabled == 0) {
                appUser.setEnabled(1);
            }
            userService.updateUser(appUser);
        });
        return "redirect:/admin/dashboard";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        AppUser appUser = userService.getUserById(id);
        Seat seat = appUser.getSeat();

        appUser.setRoles(null);
        appUser.setSeat(null);
        userService.updateUser(appUser);

        seat.setFlight(null);
        seatService.update(seat);
        seatService.delete(seat);

//        userService.detachUserFromRole(appUser.getId());
        userService.delete(appUser);

        return "redirect:/admin/dashboard";
    }

}
