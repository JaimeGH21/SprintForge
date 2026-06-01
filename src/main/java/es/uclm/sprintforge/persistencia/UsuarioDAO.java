package es.uclm.sprintforge.persistencia;
import es.uclm.sprintforge.dominio.Usuario;

public class UsuarioDAO extends AbstractEntityDAO {

    public UsuarioDAO() {
        super();
    }

    @Override
    public void saveEntity() {
        System.out.println("Usuario guardado en la base de datos.");
    }

    @Override
    public Usuario selectEntity() {
        return new Usuario();
    }


  @Override
    public void updateEntity() {
    }

    @Override
    public void deleteEntity() {
    }
}
