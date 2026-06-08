package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.uclm.sprintforge.negocio.GestorInquilinos;

@Controller
public class RegistroInquilinoController {

    @Autowired
    private GestorInquilinos gestor;

    @GetMapping("/registroInquilino")
    public String verRegistro() {
        return "registroInquilino"; 
    }

    @PostMapping("/registroInquilino")
    public String registrarInquilino(
            @RequestParam String login, 
            @RequestParam String pass, 
            @RequestParam String nombre, 
            @RequestParam String apellidos,
            @RequestParam String direccion) {
        
        if (gestor.registrarInquilino(login, pass, nombre, apellidos, direccion)) {
            return "redirect:/login";
        } else {
            return "redirect:/registroInquilino?error";
        }
    }
}