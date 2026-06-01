package es.uclm.sprintforge.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBD {
    private static final String URL = "jdbc:sqlite:sprintforge.db";
    public GestorBD() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver de SQLite: " + e.getMessage());
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }


    public void executeUpdate(String sql) {
        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("SQL ejecutado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al ejecutar UPDATE/INSERT/DELETE: " + e.getMessage());
        }
    }


    public void insert(String sql) {
        executeUpdate(sql);
    }

    public void update(String sql) {
        executeUpdate(sql);
    }

    public void delete(String sql) {
        executeUpdate(sql);
    }

    public AbstractEntityDAO select(String sql) {
        System.out.println("Ejecutando consulta SELECT: " + sql);
        return null; 
    }
}
