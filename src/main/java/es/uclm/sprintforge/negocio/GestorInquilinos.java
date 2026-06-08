package es.uclm.sprintforge.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.uclm.sprintforge.dominio.Inquilino;
import es.uclm.sprintforge.persistencia.InquilinoDAO;

@Service
public class GestorInquilinos {
    @Autowired
    private InquilinoDAO inquilinoDAO;

    public boolean registrarInquilino(String login, String pass, String nombre, String apellidos, String direccion) {
        if (inquilinoDAO.existsById(login)) { 
            return false; 
        }
        Inquilino nuevoInquilino = new Inquilino(login, pass, nombre, apellidos, direccion);
        inquilinoDAO.save(nuevoInquilino);
        return true;
    }
}