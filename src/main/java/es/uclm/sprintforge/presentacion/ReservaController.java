package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import es.uclm.sprintforge.dominio.Reserva;
import es.uclm.sprintforge.negocio.GestorReservas;
import jakarta.servlet.http.HttpSession;
import es.uclm.sprintforge.dominio.Usuario;

@Controller
public class ReservaController {
    @Autowired
    private GestorReservas gestor;

    @GetMapping("/reservas")
    public String verReservas(Model model, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        if (user == null) return "redirect:/login";
        model.addAttribute("reservas", gestor.obtenerReservas(user));
        return "reservas";
    }

    @PostMapping("/reservas")
    public String crearReserva(@RequestParam String descripcion, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        if (user != null) {
            gestor.guardar(new Reserva(descripcion, user));
        }
        return "redirect:/reservas";
    }
}