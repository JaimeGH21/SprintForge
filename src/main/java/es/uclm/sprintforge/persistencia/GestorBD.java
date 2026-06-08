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
            crearTablas(); 
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

    private void crearTablas() {
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS Usuarios ("
                + "login VARCHAR(50) PRIMARY KEY, "
                + "password VARCHAR(50) NOT NULL"
                + ");";

        String sqlInmuebles = "CREATE TABLE IF NOT EXISTS Inmuebles ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "direccion VARCHAR(100), "
                + "precioNoche REAL, "
                + "politicaCancelacion VARCHAR(50), "
                + "propietario_login VARCHAR(50), "
                + "FOREIGN KEY(propietario_login) REFERENCES Usuarios(login)"
                + ");";

        String sqlReservas = "CREATE TABLE IF NOT EXISTS Reservas ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "fechaInicio VARCHAR(20), "
                + "fechaFin VARCHAR(20), "
                + "pagado INTEGER, "
                + "activa INTEGER, "
                + "inquilino_login VARCHAR(50), "
                + "inmueble_id INTEGER, "
                + "FOREIGN KEY(inquilino_login) REFERENCES Usuarios(login), "
                + "FOREIGN KEY(inmueble_id) REFERENCES Inmuebles(id)"
                + ");";

        this.executeUpdate(sqlUsuarios);
        this.executeUpdate(sqlInmuebles);
        this.executeUpdate(sqlReservas);
        System.out.println("Tablas comprobadas/creadas con éxito.");
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
