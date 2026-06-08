package es.uclm.sprintforge.negocio;

import es.uclm.sprintforge.dominio.Inmueble;
import es.uclm.sprintforge.dominio.Propietario;
import es.uclm.sprintforge.persistencia.InmuebleDAO;

public class GestorInmuebles {

    private InmuebleDAO inmuebleDAO;

    public GestorInmuebles() {
        this.inmuebleDAO = new InmuebleDAO();
    }

    public boolean registrarPropiedad(Inmueble inmueble, Propietario propietario) {
        if (inmueble.getDireccion() == null || inmueble.getDireccion().trim().isEmpty()) {
            System.out.println("Error: No se puede registrar un inmueble sin dirección.");
            return false; 
        }


        System.out.println("Validación correcta. Guardando inmueble en: " + inmueble.getDireccion());
        
        return inmuebleDAO.insertarInmueble(inmueble); 
    }
}