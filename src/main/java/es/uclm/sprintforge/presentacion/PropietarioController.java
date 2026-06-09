package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import es.uclm.sprintforge.negocio.GestorReservas;
import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.dominio.Reserva;
import es.uclm.sprintforge.dominio.SolicitudReserva;
import es.uclm.sprintforge.persistencia.ReservaDAO;
import java.util.List;

@Controller
@RequestMapping("/propietario")
public class PropietarioController {

    @Autowired
    private GestorReservas gestorReservas;

    @Autowired
    private ReservaDAO reservaDAO;

    @GetMapping("/solicitudes")
    public String verSolicitudes(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        // 1. Imprime todo lo que existe en la BD
        List<Reserva> todas = reservaDAO.findAll();
        System.out.println("DEBUG: Número total de reservas en la BD: " + todas.size());
        for(Reserva r : todas) {
            System.out.println("DEBUG: Reserva ID: " + r.getId() + " - ¿Activa?: " + r.isActiva());
        }

        // 2. Imprime lo que encuentra tu filtro
        List<SolicitudReserva> pendientes = gestorReservas.getSolicitudesPendientes();
        System.out.println("DEBUG: Solicitudes pendientes encontradas: " + pendientes.size());

        model.addAttribute("solicitudes", pendientes);
        return "Solicitudes";
    }

    @PostMapping("/confirmar/{id}")
    public String confirmar(@PathVariable Long id) {
        gestorReservas.confirmarReserva(id);
        return "redirect:/propietario/solicitudes?confirmada";
    }
}