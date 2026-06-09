package es.uclm.sprintforge.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.uclm.sprintforge.dominio.Disponibilidad;

@Repository
public interface DisponibilidadDAO extends JpaRepository<Disponibilidad, Long> {
    // Aquí puedes añadir métodos de consulta específicos si los necesitas en el futuro
}