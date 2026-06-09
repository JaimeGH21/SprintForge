package es.uclm.sprintforge.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.uclm.sprintforge.dominio.*;
import es.uclm.sprintforge.persistencia.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GestorInmuebles {

    @Autowired private InmuebleDAO inmuebleDAO;
    @Autowired private DisponibilidadDAO disponibilidadDAO;

    public boolean publicarInmueble(String dir, double prec, String desc, String fInicio, String fFin, boolean directa) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date inicio = sdf.parse(fInicio);
            Date fin = sdf.parse(fFin);

            if (inmuebleDAO.countSolapamientos(inicio, fin) > 0) {
                return false;
            }

            Inmueble i = new Inmueble(dir, prec, desc, directa);
            inmuebleDAO.save(i);

            Disponibilidad d = new Disponibilidad();
            d.setInmueble(i);
            d.setFechaInicio(inicio);
            d.setFechaFin(fin);
            disponibilidadDAO.save(d);
            return true;
        } catch (Exception e) { 
            return false; 
        }
    }
    public List<Inmueble> obtenerTodos() { 
        return inmuebleDAO.findAll(); 
    }

    public List<Inmueble> buscarPorFechas(String iStr, String fStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return inmuebleDAO.findDisponibles(sdf.parse(iStr), sdf.parse(fStr));
        } catch (Exception e) { 
            return inmuebleDAO.findAll(); 
        }
    }
}