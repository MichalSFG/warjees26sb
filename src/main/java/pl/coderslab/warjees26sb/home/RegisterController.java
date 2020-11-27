package pl.coderslab.warjees26sb.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.warjees26sb.users.AppUser;

@Controller
public class RegisterController {

    @RequestMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }
}
