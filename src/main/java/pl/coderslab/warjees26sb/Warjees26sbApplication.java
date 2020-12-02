package pl.coderslab.warjees26sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.HandlerExceptionResolver;

@SpringBootApplication
@EnableScheduling
public class Warjees26sbApplication {

    public static void main(String[] args) {
        SpringApplication.run(Warjees26sbApplication.class, args);
    }

}
