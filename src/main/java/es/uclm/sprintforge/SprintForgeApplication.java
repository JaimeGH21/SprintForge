package es.uclm.sprintforge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// @ComponentScan le dice a Spring que busque los paquetes dentro de "es.uclm.sprintforge"
@SpringBootApplication
@ComponentScan(basePackages = "es.uclm.sprintforge")
public class SprintForgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprintForgeApplication.class, args);
    }
}