package es.uclm.sprintforge.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import es.uclm.sprintforge.dominio.Reserva;
import java.util.List;
import es.uclm.sprintforge.dominio.Usuario;

public interface ReservaDAO extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuario(Usuario usuario);
}