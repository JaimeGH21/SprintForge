package es.uclm.sprintforge.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import es.uclm.sprintforge.dominio.ListaDeseos;
import es.uclm.sprintforge.dominio.Usuario;
import java.util.List;

public interface ListaDeseosDAO extends JpaRepository<ListaDeseos, Long> {
    List<ListaDeseos> findByUsuario(Usuario usuario);
}