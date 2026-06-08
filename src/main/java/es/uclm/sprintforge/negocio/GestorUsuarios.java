package es.uclm.sprintforge.negocio;

import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.persistencia.UsuarioDAO;

public class GestorUsuarios {
    
    private UsuarioDAO usuarioDAO;

    public GestorUsuarios() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // VentanaLogin llama a este método 
    public boolean comprobarCredenciales(String login, String password) {
        return usuarioDAO.verificarUsuario(login, password);
    }

    // VentanaRegistroInquilino llama a este método 
    public boolean registrarUsuario(Usuario usuario) {
        return usuarioDAO.insertarUsuario(usuario);
    }
}
