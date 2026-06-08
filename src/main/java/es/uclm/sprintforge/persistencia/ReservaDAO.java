package es.uclm.sprintforge.persistencia;

public class ReservaDAO {
    
    private GestorBD gestorBD;

    public ReservaDAO() {
        this.gestorBD = new GestorBD();
    }


    public void registrarReserva(String direccion, String fechaInicio, String fechaFin, String titular) {
        
        String sql = "INSERT INTO Reservas (direccion_inmueble, fecha_inicio, fecha_fin, titular_pago) " +
                     "VALUES ('" + direccion + "', '" + fechaInicio + "', '" + fechaFin + "', '" + titular + "');";
        
        gestorBD.insert(sql);
    }
}


