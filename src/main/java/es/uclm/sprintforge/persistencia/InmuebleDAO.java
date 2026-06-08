package es.uclm.sprintforge.persistencia;

import es.uclm.sprintforge.dominio.Inmueble;
import es.uclm.sprintforge.dominio.PoliticaCancelacion;

import java.util.ArrayList;
import java.util.List;

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

    public List<Inmueble> obtenerTodosInmuebles() {
        List<Inmueble> lista = new ArrayList<>();

        String sql = "SELECT rowid, direccion, precioNoche, politicaCancelacion FROM Inmuebles;";
        
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:sqlite:sprintforge.db");
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
             java.sql.ResultSet rs = pstmt.executeQuery()) {
            
            int contadorId = 1; 
            while (rs.next()) {
                Inmueble inmueble = new Inmueble();
                inmueble.setDireccion(rs.getString("direccion"));
                inmueble.setPrecioNoche(rs.getDouble("precioNoche"));
                
                String politicaStr = rs.getString("politicaCancelacion");
                if (politicaStr != null) {
                    inmueble.setPoliticaCancelacion(PoliticaCancelacion.valueOf(politicaStr));
                }
                
                lista.add(inmueble);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error al cargar los inmuebles desde SQLite: " + e.getMessage());
        }
        
        return lista;
    }
}

