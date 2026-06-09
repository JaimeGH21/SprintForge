package es.uclm.sprintforge.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.dominio.Inmueble;
import es.uclm.sprintforge.dominio.ListaDeseos;
import es.uclm.sprintforge.persistencia.ListaDeseosDAO;
import es.uclm.sprintforge.persistencia.UsuarioDAO;
import es.uclm.sprintforge.persistencia.InmuebleDAO;

@Controller
public class ListaDeseosController {

    @Autowired private ListaDeseosDAO wishlistDAO;
    @Autowired private UsuarioDAO usuarioDAO;
    @Autowired private InmuebleDAO inmuebleDAO;

    @PostMapping("/listaDeseos/add")
    public String add(@RequestParam String login, @RequestParam Long idInmueble) {
       
        Usuario u = usuarioDAO.findByLogin(login);
        Inmueble i = inmuebleDAO.findById(idInmueble).orElse(null);

        if (u == null) System.out.println("DEBUG ERROR: Usuario no encontrado");
        if (i == null) System.out.println("DEBUG ERROR: Inmueble no encontrado");
        
        if (u != null && i != null) {
            wishlistDAO.save(new ListaDeseos(u, i));
            return "redirect:/listarInmuebles?deseoExito";
        }
        return "redirect:/listarInmuebles?error";
    }
}