
package es.uclm.sprintforge.entity;

import java.util.ArrayList;
import java.util.List;

public class Propietario extends Usuario {
    private List<Inmueble> propiedades;

    public Propietario(String login, String pass, String nombre, String apellidos, String direccion) {
        super(login, pass, nombre, apellidos, direccion);
        this.propiedades = new ArrayList<>();
    }

    public void añadirPropiedad(Inmueble inmueble) {
        this.propiedades.add(inmueble);
    }

    public List<Inmueble> getPropiedades() { return propiedades; }
}
