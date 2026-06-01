package es.uclm.sprintforge.persistencia;

import es.uclm.sprintforge.dominio.Inmueble;

public class InmuebleDAO {
    
    private GestorBD gestorBD;

    public InmuebleDAO() {
        this.gestorBD = new GestorBD();
    }

    public void registrarInmueble(Inmueble inmueble, String propietarioLogin) {
        String sql = "INSERT INTO Inmuebles (direccion, precioNoche, politicaCancelacion, propietario_login) " +
                     "VALUES ('" + inmueble.getDireccion() + "', " + inmueble.getPrecioNoche() + ", '" +
                     inmueble.getPoliticaCancelacion() + "', '" + propietarioLogin + "');";
        
        gestorBD.insert(sql);
    }
}
