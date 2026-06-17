package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.uclm.sprintforge.negocio.GestorBusquedas; // Asegúrate de este import
import es.uclm.sprintforge.persistencia.InmuebleDAO;
import es.uclm.sprintforge.persistencia.UsuarioDAO;

@Controller
public class ListadoController {

    @Autowired
    private InmuebleDAO inmuebleDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private GestorBusquedas gestorBusquedas; 

    @GetMapping("/listarInmuebles")
    public String listar(@RequestParam(required = false) String destino, 
                         @RequestParam(required = false) String fechaInicio, 
                         @RequestParam(required = false) String fechaFin,
                         Model model) {
        
        // Si no hay filtros, listar todo
        if (destino == null && fechaInicio == null) {
            model.addAttribute("lista", inmuebleDAO.findAll());
        } else {
            // Si hay filtros, llamar a la lógica de Jaime
            model.addAttribute("lista", gestorBusquedas.buscarAlojamiento(destino, fechaInicio, fechaFin));
        }
        
        model.addAttribute("listaUsuarios", usuarioDAO.findAll()); 
        return "listarInmuebles";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String destino, 
                         @RequestParam(required = false) String fechaInicio, 
                         @RequestParam(required = false) String fechaFin,
                         Model model) {
        
        // Si las fechas vienen vacías, dales un valor por defecto o maneja el nulo
        if (fechaInicio == null || fechaInicio.isEmpty()) fechaInicio = "";
        if (fechaFin == null || fechaFin.isEmpty()) fechaFin = "";
        
        model.addAttribute("lista", gestorBusquedas.buscarAlojamiento(destino, fechaInicio, fechaFin));
        model.addAttribute("listaUsuarios", usuarioDAO.findAll());
        
        return "listarInmuebles";
    }
    }
