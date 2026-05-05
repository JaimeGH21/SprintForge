package es.uclm.sprintforge.controller;

import es.uclm.sprintforge.entity.Usuario;

public class GestorUsuarios {
    
    public void registrarUsuario(Usuario u) {
        // Lógica para validar datos antes de guardar
        System.out.println("Registrando al usuario: " + u.getNombre());
    }

    public boolean login(String login, String pass) {
        // Lógica para comprobar si el usuario existe
        return true;
    }
}
