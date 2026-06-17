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

    public List<Inmueble> buscarAvanzado(String iStr, String fStr, double precioMax, String ciudad) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date inicio = sdf.parse(iStr);
            Date fin = sdf.parse(fStr);
            
            // Protección: Si la ciudad viene vacía, le ponemos un string vacío para que busque en todas
            String ciudadFiltro = (ciudad != null) ? ciudad.trim() : "";
            
            // Protección: Si el precio viene a 0 o negativo, ponemos un tope altísimo para que no filtre por precio
            double precioFiltro = (precioMax > 0) ? precioMax : 999999.0;

            return inmuebleDAO.findFiltrosAvanzados(inicio, fin, precioFiltro, ciudadFiltro);
        } catch (Exception e) {
            System.out.println("DEBUG ERROR FILTROS: " + e.getMessage());
            return inmuebleDAO.findAll(); // Fallback de seguridad para que la web no se caiga
        }
    }
}