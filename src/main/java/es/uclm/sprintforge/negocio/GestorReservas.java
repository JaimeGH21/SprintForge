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

    // LO QUE FUNCIONA NO SE TOCA: Tus métodos originales siguen igual
    public void guardar(Reserva reserva) {
        reservaDAO.save(reserva);
    }

    public List<Reserva> obtenerReservas(Usuario usuario) {
        return reservaDAO.findByUsuario(usuario);
    }

    // NUEVO MÉTODO: Implementa el diagrama Inmediata vs Solicitud
    public Reserva procesarReserva(Usuario usuario, Inmueble inmueble, Date fechaInicio, Date fechaFin, String descripcion) {
        Reserva nuevaReserva;

        if (inmueble.isDirecta()) {
            // 1. Reserva Inmediata (vía libre)
            nuevaReserva = new Reserva(descripcion, usuario, inmueble, fechaInicio, fechaFin);
            nuevaReserva.setActiva(true);
            nuevaReserva.setPagado(true); // El enunciado dice que se completa el pago aquí
        } else {
            // 2. Solicitud de Reserva (hay que esperar al propietario)
            SolicitudReserva solicitud = new SolicitudReserva(descripcion, usuario, inmueble, fechaInicio, fechaFin);
            solicitud.setConfirmada(false);
            solicitud.setActiva(false); // Desactivada hasta que se confirme
            solicitud.setPagado(false);
            nuevaReserva = solicitud;
        }

        reservaDAO.save(nuevaReserva);
        return nuevaReserva;
    }
}