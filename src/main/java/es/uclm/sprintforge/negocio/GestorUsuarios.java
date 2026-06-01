package es.uclm.sprintforge.negocio; 

import es.uclm.sprintforge.dominio.Usuario; 

public class GestorUsuarios { 
    
    public void registrarUsuario(Usuario u) {
        System.out.println("Registrando al usuario: " + u.getNombre());
    }

    public boolean login(String login, String pass) {
        return true;
    }
}

