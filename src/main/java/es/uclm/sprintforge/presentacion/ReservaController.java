package es.uclm.sprintforge.presentacion;

import es.uclm.sprintforge.dominio.Notificaciones;
import es.uclm.sprintforge.persistencia.NotificacionesDAO;
import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.persistencia.UsuarioDAO; // Asegúrate de que tu DAO de usuarios se llama así
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservaController {

    @Autowired
    private NotificacionesDAO notificacionesDAO;

    @Autowired
    private UsuarioDAO usuarioDAO; // Inyectamos el DAO de usuarios para poder listarlos


    @GetMapping("/reservas")
    public String mostrarFormulario() {
        return "reservas"; 
    }

    // Procesa la reserva y la notificación
    @PostMapping("/reservas")
    public String realizarReserva(
            @RequestParam(value = "loginUsuario", required = false) String loginUsuario, // CORREGIDO: Coincide con name="loginUsuario" del HTML
            @RequestParam(value = "idInmueble", required = false) String idInmueble) {   // CORREGIDO: Coincide con name="idInmueble" del HTML

        // Al coincidir los nombres con el formulario, ya tomará el login introducido correctamente
        String destinatario = (loginUsuario != null && !loginUsuario.isEmpty()) ? loginUsuario : "UsuarioAnonimo";
        
        Notificaciones nuevaNotificacion = new Notificaciones(destinatario, "Reserva confirmada para el inmueble: " + idInmueble);
        notificacionesDAO.save(nuevaNotificacion);
        
        return "redirect:/misNotificaciones";
    }

    @GetMapping("/misNotificaciones")
    public String listarNotificaciones(Model model) {
        model.addAttribute("listaNotificaciones", notificacionesDAO.findAll());
        return "notificaciones";
    }

    @GetMapping("/listarUsuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioDAO.findAll());
        return "usuarios"; // Devuelve la plantilla usuarios.html
    }
}