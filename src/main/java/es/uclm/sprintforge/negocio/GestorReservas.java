package es.uclm.sprintforge.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.uclm.sprintforge.dominio.Reserva;
import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.persistencia.ReservaDAO;
import java.util.List;

@Service
public class GestorReservas {
    @Autowired
    private ReservaDAO reservaDAO;

    public void guardar(Reserva reserva) {
        reservaDAO.save(reserva);
    }

    public List<Reserva> obtenerReservas(Usuario usuario) {
        return reservaDAO.findByUsuario(usuario);
    }
}