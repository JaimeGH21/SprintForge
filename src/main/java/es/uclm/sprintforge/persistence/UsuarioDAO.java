package es.uclm.sprintforge.persistence;

import es.uclm.sprintforge.entity.Usuario;

public class UsuarioDAO {
    
    public void insertar(Usuario u) {
        // Aquí irá la sentencia SQL: INSERT INTO Usuarios...
        System.out.println("Usuario guardado en la base de datos.");
    }

    public Usuario leer(String login) {
        // Aquí irá la sentencia SQL: SELECT * FROM Usuarios...
        return new Usuario();
    }
}
