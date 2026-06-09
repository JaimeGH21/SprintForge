package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import es.uclm.sprintforge.dominio.Inmueble;
import es.uclm.sprintforge.persistencia.InmuebleDAO;

@Controller
@RequestMapping("/inmueble")
public class InmuebleController {

    @Autowired
    private InmuebleDAO inmuebleDAO;

    @GetMapping("/nuevo")
    public String mostrarFormulario() {
        return "anadirInmueble";
    }

    @PostMapping("/guardar")
    public String guardarInmueble(@RequestParam String direccion, 
                                  @RequestParam double precio, 
                                  @RequestParam String descripcion,
                                  @RequestParam(defaultValue = "true") boolean directa) {
        
        Inmueble i = new Inmueble(direccion, precio, descripcion, directa);
        inmuebleDAO.save(i);
        return "redirect:/home?creado";
    }

    // --- LO NUEVO: ELIMINAR ---
    @PostMapping("/eliminar")
    public String eliminarInmueble(@RequestParam Long id) {
        inmuebleDAO.deleteById(id);
        return "redirect:/listarInmuebles?eliminado";
    }
}