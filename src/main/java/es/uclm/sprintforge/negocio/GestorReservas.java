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

    public void guardar(Reserva reserva) {
        reservaDAO.save(reserva);
    }

    public List<Reserva> obtenerReservas(Usuario usuario) {
        return reservaDAO.findByUsuario(usuario);
    }

    public Reserva procesarReserva(Usuario usuario, Inmueble inmueble, Date fechaInicio, Date fechaFin, String descripcion) {
        Reserva nuevaReserva;

        if (inmueble.isDirecta()) {
            nuevaReserva = new Reserva(descripcion, usuario, inmueble, fechaInicio, fechaFin);
            nuevaReserva.setActiva(true);
            nuevaReserva.setPagado(true);
        } else {
            SolicitudReserva solicitud = new SolicitudReserva(descripcion, usuario, inmueble, fechaInicio, fechaFin);
            solicitud.setConfirmada(false);
            solicitud.setActiva(false);
            solicitud.setPagado(false);
            nuevaReserva = solicitud;
        }

        reservaDAO.save(nuevaReserva);
        return nuevaReserva;
    }

    // --- NUEVOS MÉTODOS ---
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
        }
    }
}