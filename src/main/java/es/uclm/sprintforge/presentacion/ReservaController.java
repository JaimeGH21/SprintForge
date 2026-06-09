package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.uclm.sprintforge.dominio.Inmueble;
import es.uclm.sprintforge.dominio.Reserva;
import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.persistencia.InmuebleDAO;
import es.uclm.sprintforge.persistencia.ReservaDAO;
import es.uclm.sprintforge.persistencia.UsuarioDAO;
import java.util.Date;

@Controller
public class ReservaController {

    @Autowired
    private ReservaDAO reservaDAO;

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

        if (inmueble != null && usuario != null) {
            // Creamos y guardamos la reserva
            Reserva nuevaReserva = new Reserva("Reserva desde la web", usuario, inmueble, fechaInicio, fechaFin);
            reservaDAO.save(nuevaReserva);
            
            return "redirect:/listarInmuebles?reservaExito";
        }

        return "redirect:/listarInmuebles?errorReserva";
    }
}