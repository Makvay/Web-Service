package application.empl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "EMPL Application is running! 🚀";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is healthy! ✅";
    }
}