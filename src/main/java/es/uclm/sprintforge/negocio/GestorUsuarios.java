package es.uclm.sprintforge.negocio;

import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.persistencia.UsuarioDAO;

public class GestorUsuarios {
    private UsuarioDAO usuarioDAO;

    public GestorUsuarios() {
        this.usuarioDAO = new UsuarioDAO();
    }

    
    public Usuario login(String login, String password) {
        // Llamamos al método exacto de tu UsuarioDAO
        Usuario usuario = usuarioDAO.selectEntity();
        
        if (usuario != null) {
            return usuario; 
        }
        
        return null;
    }

    
    public boolean registrarUsuario(Usuario usuario) {
        // Llamamos al método de guardado de tu UsuarioDAO
        usuarioDAO.saveEntity();
                return true;
    }
}
