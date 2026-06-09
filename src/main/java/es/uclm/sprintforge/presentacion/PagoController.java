package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.uclm.sprintforge.dominio.Reserva;
import es.uclm.sprintforge.persistencia.ReservaDAO;

@Controller
public class PagoController {

    @Autowired
    private ReservaDAO reservaDAO;

    @PostMapping("/pagar")
    public String procesarPago(@RequestParam Long idReserva) {
        reservaDAO.findById(idReserva).ifPresent(reserva -> {
            reserva.setPagado(true);
            reservaDAO.save(reserva);
        });
        return "redirect:/listarInmuebles?pagoExitoso";
    }
}