package es.uclm.sprintforge.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.uclm.sprintforge.dominio.Inmueble;
import es.uclm.sprintforge.persistencia.InmuebleDAO;
import java.util.List;

@Service
public class GestorInmuebles {
    @Autowired
    private InmuebleDAO inmuebleDAO;

    public void publicarInmueble(String direccion, double precio, String desc) {
        Inmueble i = new Inmueble(direccion, precio, desc);
        inmuebleDAO.save(i);
    }

    public List<Inmueble> obtenerTodos() {
        return inmuebleDAO.findAll();
    }
}