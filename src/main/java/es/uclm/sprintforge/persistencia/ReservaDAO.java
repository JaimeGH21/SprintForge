package es.uclm.sprintforge.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.uclm.sprintforge.dominio.Reserva;
import es.uclm.sprintforge.dominio.SolicitudReserva;
import es.uclm.sprintforge.dominio.Usuario;
import java.util.List;

public interface ReservaDAO extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuario(Usuario usuario);

    @Query("SELECT s FROM SolicitudReserva s WHERE s.confirmada = false")
    List<SolicitudReserva> findAllSolicitudesPendientes();
}