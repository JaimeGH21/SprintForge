package es.uclm.sprintforge.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import es.uclm.sprintforge.dominio.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}