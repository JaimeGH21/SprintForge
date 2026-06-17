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

    // 1. Inyectamos la capa de negocio (Gestor) en lugar del DAO de reservas
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
            // 2. Usamos el Gestor. Él se encarga de validar fechas, solapamientos y tipos de reserva
            gestorReservas.procesarReserva(usuario, inmueble, fechaInicio, fechaFin, "Reserva desde la web");
            
            return "redirect:/listarInmuebles?reservaExito=true";
            
        } catch (IllegalArgumentException e) {
            // 3. Si el Gestor detecta que el piso está ocupado, salta aquí y no explota la web
            System.out.println("DEBUG ERROR RESERVA: " + e.getMessage());
            return "redirect:/listarInmuebles?errorReserva=fechasOcupadas";
        }
    }
}