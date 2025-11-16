package application.empl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("application.empl.Repo")
@EntityScan("application.empl.Entities") // Исправлено на Entities (с i)
public class EmplApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmplApplication.class, args);
    }
}