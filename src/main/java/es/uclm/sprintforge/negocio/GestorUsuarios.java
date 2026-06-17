package es.uclm.sprintforge.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.persistencia.UsuarioDAO;

@Service
public class GestorUsuarios {
    
    @Autowired
    private UsuarioDAO usuarioDAO;

    // Constructor vacío (necesario para Spring)
    public GestorUsuarios() {}

    // Constructor para el test (inyección manual)
    public GestorUsuarios(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public boolean validarUsuario(String login, String pass) {
        if (usuarioDAO == null) return false;
        Usuario u = usuarioDAO.findByLogin(login);
        return u != null && u.getPass().equals(pass);
    }
    
    public Usuario buscarUsuario(String login) {
        if (usuarioDAO == null) return null;
        return usuarioDAO.findByLogin(login);
    }
}