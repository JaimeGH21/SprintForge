package es.uclm.sprintforge.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.persistencia.UsuarioDAO;

@Service
public class GestorUsuarios {
    
    @Autowired
    private UsuarioDAO usuarioDAO;

    public boolean validarUsuario(String login, String pass) {
        Usuario u = usuarioDAO.findByLogin(login);
        return u != null && u.getPass().equals(pass);
    }
    
    public Usuario buscarUsuario(String login) {
        return usuarioDAO.findByLogin(login);
    }
}