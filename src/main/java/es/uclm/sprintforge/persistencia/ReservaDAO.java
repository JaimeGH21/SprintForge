package es.uclm.sprintforge.persistencia;

import es.uclm.sprintforge.dominio.Reserva;

public class ReservaDAO {

    private GestorBD gestorBD;

    public ReservaDAO() {
        this.gestorBD = new GestorBD();
    }


  public void guardarReserva(Reserva r, String inquilinoLogin, int inmuebleId) {
        int pagadoNumeric = r.isPagado() ? 1 : 0; 
        
        String sql = "INSERT INTO Reservas (fechaInicio, fechaFin, pagado, activa, inquilino_login, inmueble_id) " +
                     "VALUES ('" + r.getFechaInicio() + "', '" + r.getFechaFin() + "', " +
                     pagadoNumeric + ", 1, '" + inquilinoLogin + "', " + inmuebleId + ");";
        
        gestorBD.insert(sql);
    }
}
