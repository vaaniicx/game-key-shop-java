package at.vaaniicx.lap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Hinter @SpringBootApplication ->
// @Configuration @EnableAutoConfiguration und @ComponentScan
@SpringBootApplication
@EnableJpaRepositories("at.vaaniicx.lap.repository")
@EntityScan("at.vaaniicx.lap.model")
public class StarterClass {

    public static void main(String[] args) {
        SpringApplication.run(StarterClass.class, args);
    }
}