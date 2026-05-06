package es.uclm.sprintforge.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBD {
    // El archivo de la base de datos se creará en la raíz de tu proyecto en Eclipse
    private static final String URL = "jdbc:sqlite:sprintforge.db";

    public static Connection conectar() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conn;
    }

    public static void inicializarBD() {
        // Creamos la tabla de inmuebles siguiendo el dominio que ya definimos[cite: 1]
        String sql = "CREATE TABLE IF NOT EXISTS inmuebles (" +
                     "id TEXT PRIMARY KEY," +
                     "direccion TEXT NOT NULL," +
                     "precio REAL NOT NULL," +
                     "disponible INTEGER DEFAULT 1);";
        
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Base de datos SQLite inicializada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al inicializar la BD: " + e.getMessage());
        }
    }
}
