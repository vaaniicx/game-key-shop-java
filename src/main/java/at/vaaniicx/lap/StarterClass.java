package at.vaaniicx.lap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories("at.vaaniicx.lap.repository")
@EntityScan("at.vaaniicx.lap.model")
public class StarterClass {

    /***
     * Einstiegspunkt der Applikation
     *
     * @param args - Start-Parameter
     */
    public static void main(String[] args) {
        SpringApplication.run(StarterClass.class, args);
    }
}