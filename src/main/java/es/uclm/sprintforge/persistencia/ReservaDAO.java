package es.uclm.sprintforge.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import es.uclm.sprintforge.dominio.Reserva;
import es.uclm.sprintforge.dominio.SolicitudReserva;
import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.dominio.Inmueble;
import java.util.List;
import java.util.Date;

public interface ReservaDAO extends JpaRepository<Reserva, Long> {
    
    List<Reserva> findByUsuario(Usuario usuario);

    @Query("SELECT s FROM SolicitudReserva s WHERE s.confirmada = false")
    List<SolicitudReserva> findAllSolicitudesPendientes();

    @Query("SELECT r FROM Reserva r WHERE r.inmueble = :inmueble AND r.activa = true AND " +
           "(r.fechaInicio <= :fechaFin AND r.fechaFin >= :fechaInicio)")
    List<Reserva> findReservasSolapadas(@Param("inmueble") Inmueble inmueble, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
}