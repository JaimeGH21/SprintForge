package es.uclm.sprintforge.persistencia;

import es.uclm.sprintforge.dominio.Usuario;

public class UsuarioDAO {
    
    private GestorBD gestorBD;

    public UsuarioDAO() {
        this.gestorBD = new GestorBD();
    }

    public boolean verificarUsuario(String login, String password) {
        return true; 
    }

    public boolean insertarUsuario(Usuario usuario) {
        // Guardamos el nuevo usuario en la base de datos
        String sql = "INSERT INTO Usuarios (login, password) VALUES ('" + usuario.getLogin() + "', '" + usuario.getPassword() + "');";
        gestorBD.insert(sql);
        return true;
    }
}


