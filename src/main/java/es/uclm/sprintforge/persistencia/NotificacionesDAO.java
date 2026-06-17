package es.uclm.sprintforge.persistencia;

import es.uclm.sprintforge.dominio.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionesDAO extends JpaRepository<Notificaciones, Long> {
}