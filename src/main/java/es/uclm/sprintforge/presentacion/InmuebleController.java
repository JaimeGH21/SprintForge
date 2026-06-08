package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.uclm.sprintforge.negocio.GestorInmuebles;

@Controller
public class InmuebleController {

    @Autowired
    private GestorInmuebles gestorInmuebles;

    @GetMapping("/listarInmuebles")
    public String listarInmuebles(Model model) {
        model.addAttribute("lista", gestorInmuebles.obtenerTodos());
        return "listarInmuebles";
    }

    @PostMapping("/guardarInmueble")
    public String guardarInmueble(@RequestParam String direccion, 
                                  @RequestParam double precio, 
                                  @RequestParam String descripcion) {
        gestorInmuebles.publicarInmueble(direccion, precio, descripcion);
        return "redirect:/listarInmuebles";
    }
}