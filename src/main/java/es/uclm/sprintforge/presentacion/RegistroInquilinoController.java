package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.uclm.sprintforge.dominio.Inquilino;
import es.uclm.sprintforge.persistencia.UsuarioDAO;

@Controller
public class RegistroInquilinoController {

    @Autowired
    private UsuarioDAO usuarioDAO;

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
        
        try {
            // 1. Comprueba si el usuario existe
            if (usuarioDAO.findByLogin(login) != null) {
                return "redirect:/registroInquilino?error=existe";
            }

            // 2. Intenta guardar el nuevo inquilino
            Inquilino nuevoInquilino = new Inquilino(login, pass, nombre, apellidos, direccion);
            usuarioDAO.save(nuevoInquilino);
            
            // 3. Si todo va bien, al login
            return "redirect:/login";

        } catch (Exception e) {
            // ESCUDO ANTI-500: Si la base de datos falla, no rompemos la web.
            System.out.println("====== ERROR REAL EN BASE DE DATOS ======");
            System.out.println("Causa del fallo: " + e.getCause());
            System.out.println("=========================================");
            
            // Redirigimos de forma segura
            return "redirect:/registroInquilino?error=bd";
        }
    }
}