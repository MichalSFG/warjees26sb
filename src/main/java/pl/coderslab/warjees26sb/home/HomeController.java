package pl.coderslab.warjees26sb.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.warjees26sb.home.animal.AnimalRepository;

@Controller
@Slf4j
public class HomeController {

    private final AnimalRepository animalRepository;

    public HomeController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @RequestMapping("/")
    public String home() {
        animalRepository.findByNameBlaBla("burek");
        return "home";
    }

    @RequestMapping("/theme")
    public String theme(){

        log.info("asdasd {}", 12);
        return "index";
    }
}
