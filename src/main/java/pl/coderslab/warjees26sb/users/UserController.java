package pl.coderslab.warjees26sb.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        AppUser appUser = new AppUser();
        appUser.setFirstName("Jan");
        appUser.setLastName("Nowak");
        appUser.setEmail("admin@admin");
        appUser.setPassword("admin");
        userService.saveUser(appUser);
        return "added";
    }
}
