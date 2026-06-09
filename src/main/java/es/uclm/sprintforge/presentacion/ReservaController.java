package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import es.uclm.sprintforge.dominio.Inmueble;
import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.negocio.GestorReservas;
import es.uclm.sprintforge.persistencia.InmuebleDAO;
import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ReservaController {
    
    @Autowired
    private GestorReservas gestor;

    @Autowired
    private InmuebleDAO inmuebleDAO;

    @GetMapping("/reservas")
    public String verReservas(Model model, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        if (user == null) return "redirect:/login";
        model.addAttribute("reservas", gestor.obtenerReservas(user));
        return "reservas";
    }

    @PostMapping("/reservas")
    public String crearReserva(@RequestParam String descripcion, 
                               @RequestParam Long inmuebleId,
                               @RequestParam String fechaInicioStr,
                               @RequestParam String fechaFinStr,
                               HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        
        if (user != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = sdf.parse(fechaInicioStr);
                Date fechaFin = sdf.parse(fechaFinStr);
                
                // Buscamos el inmueble completo en la BD usando su ID
                Inmueble inmueble = inmuebleDAO.findById(inmuebleId).orElse(null);
                
                if (inmueble != null) {
                    // Llamamos a la nueva lógica que decide si es Inmediata o Solicitud
                    gestor.procesarReserva(user, inmueble, fechaInicio, fechaFin, descripcion);
                }
            } catch (Exception e) {
                // Si hay error en las fechas, redirigimos de vuelta sin romper la app
                return "redirect:/reservas?error=formato";
            }
        }
        return "redirect:/reservas";
    }
}