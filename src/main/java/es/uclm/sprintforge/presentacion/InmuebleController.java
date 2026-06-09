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

    @GetMapping("/buscar")
    public String buscarInmuebles(@RequestParam String fechaInicio, 
                                  @RequestParam String fechaFin, 
                                  Model model) {
        model.addAttribute("lista", gestorInmuebles.buscarPorFechas(fechaInicio, fechaFin));
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);
        return "listarInmuebles";
    }

    @PostMapping("/guardarInmueble")
    public String guardarInmueble(@RequestParam String direccion, 
                                  @RequestParam double precio, 
                                  @RequestParam String descripcion,
                                  @RequestParam String fechaInicio,
                                  @RequestParam String fechaFin,
                                  Model model) {
        boolean guardado = gestorInmuebles.publicarInmueble(direccion, precio, descripcion, fechaInicio, fechaFin);
        if (!guardado) {
            model.addAttribute("error", "¡Conflicto de fechas! El inmueble ya está ocupado en ese periodo.");
        }
        model.addAttribute("lista", gestorInmuebles.obtenerTodos());
        return "listarInmuebles";
    }
}