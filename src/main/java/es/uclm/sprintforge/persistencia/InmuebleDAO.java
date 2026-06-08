package es.uclm.sprintforge.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import es.uclm.sprintforge.dominio.Inmueble;

public interface InmuebleDAO extends JpaRepository<Inmueble, Long> {
}