package es.uclm.sprintforge.logic;

import es.uclm.sprintforge.entity.Inmueble;
import es.uclm.sprintforge.persistence.GestorBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorInmuebles {

    public void darAltaInmueble(Inmueble inmueble) {
        String sql = "INSERT INTO inmuebles(id, direccion, precio, disponible) VALUES(?,?,?,?)";

        try (Connection conn = GestorBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, inmueble.getId());
            pstmt.setString(2, inmueble.getDireccion());
            pstmt.setDouble(3, inmueble.getPrecio());
            pstmt.setInt(4, 1); // 1 = Disponible
            pstmt.executeUpdate();
            System.out.println("Inmueble guardado en BD con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al guardar inmueble: " + e.getMessage());
        }
    }

    public List<Inmueble> obtenerTodos() {
        List<Inmueble> lista = new ArrayList<>();
        String sql = "SELECT * FROM inmuebles";

        try (Connection conn = GestorBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Inmueble i = new Inmueble(
                    rs.getString("id"),
                    rs.getString("direccion"),
                    rs.getDouble("precio")
                );
                lista.add(i);
            }
        } catch (SQLException e) {
            System.out.println("Error al recuperar inmuebles: " + e.getMessage());
        }
        return lista;
    }
}
