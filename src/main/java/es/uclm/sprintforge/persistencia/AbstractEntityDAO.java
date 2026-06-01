package es.uclm.sprintforge.persistencia;

public abstract class AbstractEntityDAO {
    
    public abstract void saveEntity();
    public abstract void updateEntity();
    public abstract void deleteEntity();
    public abstract Object selectEntity();
}

