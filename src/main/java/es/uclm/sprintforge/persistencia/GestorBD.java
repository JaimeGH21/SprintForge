package es.uclm.sprintforge.persistencia;

public class GestorBD {
    
    public GestorBD() {}

    public void insert(String sql) { 
        System.out.println("Ejecutando en BD -> INSERT: " + sql);
    }

    public void update(String sql) { 
        System.out.println("Ejecutando en BD -> UPDATE: " + sql);
    }

    public void delete(String sql) { 
        System.out.println("Ejecutando en BD -> DELETE: " + sql);
    }


  public AbstractEntityDAO select(String sql) { 
        System.out.println("Ejecutando en BD -> SELECT: " + sql);
        return null; 
    }
}
