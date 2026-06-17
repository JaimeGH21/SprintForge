package es.uclm.sprintforge.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.uclm.sprintforge.dominio.*;
import es.uclm.sprintforge.persistencia.ReservaDAO;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GestorReservas {
    
    @Autowired
    private ReservaDAO reservaDAO;

    @Autowired
    private ServicioNotificaciones servicioNotificaciones;

    public void guardar(Reserva reserva) {
        reservaDAO.save(reserva);
    }

    public List<Reserva> obtenerReservas(Usuario usuario) {
        return reservaDAO.findByUsuario(usuario);
    }

    public Reserva procesarReserva(Usuario usuario, Inmueble inmueble, Date fechaInicio, Date fechaFin, String descripcion) throws IllegalArgumentException {
        
        // 1. Validar que la fecha de inicio no es posterior a la de fin
        if (fechaInicio != null && fechaFin != null && fechaInicio.after(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin.");
        }

        // 2. Comprobar que el inmueble está libre en esas fechas usando la BD
        List<Reserva> solapadas = reservaDAO.findReservasSolapadas(inmueble, fechaInicio, fechaFin);
        if (solapadas != null && !solapadas.isEmpty()) {
            throw new IllegalArgumentException("El inmueble ya está ocupado en las fechas seleccionadas.");
        }

        // 3. Crear la reserva si las fechas son válidas y están libres
        Reserva nuevaReserva;

        if (inmueble.isDirecta()) {
            nuevaReserva = new Reserva(descripcion, usuario, inmueble, fechaInicio, fechaFin);
            nuevaReserva.setActiva(true);
            nuevaReserva.setPagado(true);
            
            //Notificar éxito 
            servicioNotificaciones.enviarNotificacionReserva(usuario, nuevaReserva, "Confirmada (Reserva Directa)");
        } else {
            SolicitudReserva solicitud = new SolicitudReserva(descripcion, usuario, inmueble, fechaInicio, fechaFin);
            solicitud.setConfirmada(false);
            solicitud.setActiva(false); // Inactiva hasta que el dueño apruebe

            solicitud.setActiva(false); 
            solicitud.setPagado(false);
            nuevaReserva = solicitud;
            
            // Notificar que está pendiente de aprobación 
            servicioNotificaciones.enviarNotificacionReserva(usuario, nuevaReserva, "Pendiente de aprobación por el propietario");
        }

        reservaDAO.save(nuevaReserva);
        return nuevaReserva;
    }

    public List<SolicitudReserva> getSolicitudesPendientes() {
        return reservaDAO.findAllSolicitudesPendientes();
    }

    public void confirmarReserva(Long reservaId) {
        Reserva r = reservaDAO.findById(reservaId).orElse(null);
        if (r instanceof SolicitudReserva) {
            SolicitudReserva s = (SolicitudReserva) r;
            s.setConfirmada(true);
            s.setActiva(true); 
            reservaDAO.save(s);
            
            // Notificar al usuario que el propietario ha aceptado su solicitud 
            servicioNotificaciones.enviarNotificacionReserva(s.getUsuario(), s, "Aceptada y Confirmada por el propietario");
        }
    }
}