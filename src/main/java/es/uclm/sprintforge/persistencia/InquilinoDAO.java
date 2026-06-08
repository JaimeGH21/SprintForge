package es.uclm.sprintforge.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.uclm.sprintforge.dominio.Inquilino;

@Repository
public interface InquilinoDAO extends JpaRepository<Inquilino, String> {
}