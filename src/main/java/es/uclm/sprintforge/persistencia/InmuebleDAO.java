package es.uclm.sprintforge.persistencia;

import es.uclm.sprintforge.dominio.Inmueble;

public class InmuebleDAO {
    
    public InmuebleDAO() {}
    
    public boolean insertarInmueble(Inmueble inmueble) {
        return true;
    }
    
    public java.util.List<Inmueble> obtenerTodosInmuebles() {
        // Parche para que VentanaBusqueda no de error
        return new java.util.ArrayList<>(); 
    }

    public void registrarInmueble(Inmueble inmueble, String extra) {
        // Parche para que VentanaAltaInmueble no de error
    }
}