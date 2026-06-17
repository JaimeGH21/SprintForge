package es.uclm.sprintforge.negocio;

import org.springframework.stereotype.Service;
import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.dominio.Reserva;

@Service
public class ServicioNotificaciones {

    public void enviarNotificacionReserva(Usuario usuario, Reserva reserva, String estado) {
        System.out.println("=========================================================");
        System.out.println("[SISTEMA DE NOTIFICACIONES] Enviando correo electrónico...");
        System.out.println("Para: " + usuario.getLogin() + "@alu.uclm.es");
        System.out.println("Asunto: Actualización de tu Reserva #" + reserva.getId());
        System.out.println("Mensaje: Estimado/a usuario, su reserva para el inmueble '" 
                            + reserva.getInmueble().getDireccion() 
                            + "' ha cambiado su estado a: " + estado.toUpperCase() + ".");
        System.out.println("=========================================================");
    }
}
