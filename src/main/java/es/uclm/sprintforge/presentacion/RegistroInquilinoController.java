package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.persistencia.UsuarioDAO;

@Controller
public class RegistroInquilinoController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    // Carga tu HTML exacto
    @GetMapping("/registroInquilino")
    public String verRegistro() {
        return "registroInquilino"; 
    }

    // Guarda los datos del formulario
    @PostMapping("/registroInquilino")
    public String registrarInquilino(
            @RequestParam String login, 
            @RequestParam String pass, 
            @RequestParam String nombre, 
            @RequestParam String apellidos,
            @RequestParam String direccion) {
        
        // Comprueba si el usuario ya existe para no duplicarlo
        if (usuarioDAO.findByLogin(login) != null) {
            return "redirect:/registroInquilino?error";
        }

        // Crea el inquilino (Usuario) y lo guarda
        Usuario nuevoInquilino = new Usuario(login, pass, nombre, apellidos, direccion);
        usuarioDAO.save(nuevoInquilino);
        
        return "redirect:/login";
    }
}