package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.uclm.sprintforge.dominio.Inmueble;
import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.negocio.GestorReservas;
import es.uclm.sprintforge.persistencia.InmuebleDAO;
import es.uclm.sprintforge.persistencia.UsuarioDAO;
import java.util.Date;

@Controller
public class ReservaController {

    @Autowired
    private GestorReservas gestorReservas;

    @Autowired
    private InmuebleDAO inmuebleDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @PostMapping("/reservar")
    public String hacerReserva(@RequestParam Long idInmueble,
                               @RequestParam String loginUsuario,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {

        Inmueble inmueble = inmuebleDAO.findById(idInmueble).orElse(null);
        Usuario usuario = usuarioDAO.findByLogin(loginUsuario);

        if (inmueble == null || usuario == null) {
            System.out.println("DEBUG ERROR: Inmueble o Usuario no encontrado");
            return "redirect:/listarInmuebles?errorReserva=noEncontrado";
        }

        try {
            // Usamos el Gestor para que valide fechas y solapamientos
            gestorReservas.procesarReserva(usuario, inmueble, fechaInicio, fechaFin, "Reserva desde la web");
            return "redirect:/listarInmuebles?reservaExito=true";
            
        } catch (IllegalArgumentException e) {
            System.out.println("DEBUG ERROR RESERVA: " + e.getMessage());
            return "redirect:/listarInmuebles?errorReserva=fechasOcupadas";
        }
    }
}