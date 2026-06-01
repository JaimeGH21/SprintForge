package es.uclm.sprintforge.negocio;

import es.uclm.sprintforge.dominio.Usuario;

public class GestorNotificaciones {

    public GestorNotificaciones() {}

    public void enviarNotificacion(Usuario receptor, String mensaje) {
        System.out.println("Notificación para " + receptor.getLogin() + ": " + mensaje);
    }
}

