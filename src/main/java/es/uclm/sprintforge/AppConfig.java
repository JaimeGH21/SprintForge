package es.uclm.sprintforge; // Ajusta el paquete si la pones en otro sitio

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import es.uclm.sprintforge.negocio.GestorBusquedas;

@Configuration
public class AppConfig {

    @Bean
    public GestorBusquedas gestorBusquedas() {
        return new GestorBusquedas();
    }
}