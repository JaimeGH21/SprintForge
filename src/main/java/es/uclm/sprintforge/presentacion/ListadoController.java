package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import es.uclm.sprintforge.persistencia.InmuebleDAO;
import es.uclm.sprintforge.persistencia.UsuarioDAO;

@Controller
public class ListadoController {

    @Autowired
    private InmuebleDAO inmuebleDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @GetMapping("/listarInmuebles")
    public String listar(Model model) {
        model.addAttribute("lista", inmuebleDAO.findAll());
        // Pasamos los usuarios para ver los logins en la pantalla
        model.addAttribute("usuarios", usuarioDAO.findAll()); 
        return "listarInmuebles";
    }
}