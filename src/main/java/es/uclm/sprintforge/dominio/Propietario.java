package es.uclm.sprintforge.dominio;

import java.util.List;

public class Propietario extends Usuario {
    private List<Inmueble> inmuebles;

    public List<Inmueble> getInmuebles() { return inmuebles; }
    public void setInmuebles(List<Inmueble> inmuebles) { this.inmuebles = inmuebles; }
}