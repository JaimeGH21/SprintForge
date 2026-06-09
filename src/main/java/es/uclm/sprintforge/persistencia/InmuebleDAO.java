package es.uclm.sprintforge.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import es.uclm.sprintforge.dominio.Inmueble;
import java.util.Date;
import java.util.List;

public interface InmuebleDAO extends JpaRepository<Inmueble, Long> {

    @Query("SELECT DISTINCT i FROM Inmueble i LEFT JOIN Disponibilidad d ON i.id = d.inmueble.id " +
           "WHERE i.id NOT IN (SELECT d2.inmueble.id FROM Disponibilidad d2 " +
           "WHERE (d2.fechaInicio <= :fin AND d2.fechaFin >= :inicio))")
    List<Inmueble> findDisponibles(@Param("inicio") Date inicio, @Param("fin") Date fin);

    @Query("SELECT COUNT(d) FROM Disponibilidad d WHERE (d.fechaInicio <= :fin AND d.fechaFin >= :inicio)")
    long countSolapamientos(@Param("inicio") Date inicio, @Param("fin") Date fin);
}