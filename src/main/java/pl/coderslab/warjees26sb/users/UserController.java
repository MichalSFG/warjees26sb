package pl.coderslab.warjees26sb.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        appUser.setUsername("mike");
        appUser.setPassword("mike");
        userService.saveUser(appUser);
        return "added";
    }

}
