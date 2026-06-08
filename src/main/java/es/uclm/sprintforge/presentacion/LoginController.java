package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import es.uclm.sprintforge.negocio.GestorUsuarios;

@Controller
public class LoginController {
    @Autowired
    private GestorUsuarios gestor;

    @GetMapping("/login")
    public String mostrarLogin() { return "login"; }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String login, @RequestParam String pass, HttpSession session) {
        if (gestor.validarUsuario(login, pass)) {
            session.setAttribute("usuarioLogueado", gestor.buscarUsuario(login));
            return "redirect:/home";
        }
        return "redirect:/login?error";
    }
}