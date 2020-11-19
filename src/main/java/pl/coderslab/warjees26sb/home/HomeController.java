package pl.coderslab.warjees26sb.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.warjees26sb.home.animal.Animal;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
