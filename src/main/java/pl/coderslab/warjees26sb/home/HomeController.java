package pl.coderslab.warjees26sb.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.warjees26sb.home.animal.AnimalRepository;
import pl.coderslab.warjees26sb.users.CurrentUser;

@Controller
@Slf4j
public class HomeController {

    private final AnimalRepository animalRepository;

    public HomeController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @RequestMapping("/")
    public String home(@AuthenticationPrincipal CurrentUser customUser) {
        if (customUser != null) {
            log.info("Cześć {}", customUser.getAppUser().getUsername());
        } else
            log.info("Jesteś niezalogowany");
        return "home";
    }

    @RequestMapping("/theme")
    public String theme(){
        log.info("asdasd {}", 12);
        return "index";
    }
}
