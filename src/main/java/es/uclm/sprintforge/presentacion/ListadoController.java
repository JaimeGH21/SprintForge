package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import es.uclm.sprintforge.persistencia.InmuebleDAO;

@Controller
public class ListadoController {

    @Autowired
    private InmuebleDAO inmuebleDAO;

    @GetMapping("/listarInmuebles")
    public String listarInmuebles(Model model) {
        // Carga todos los inmuebles de la base de datos y se los pasa al HTML
        model.addAttribute("lista", inmuebleDAO.findAll());
        return "listarInmuebles"; 
    }
}