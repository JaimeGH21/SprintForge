package es.uclm.sprintforge.entity;

import java.util.ArrayList;
import java.util.List;

public class Inquilino extends Usuario {
    private List<Inmueble> listaDeseos;

    public Inquilino(String login, String pass, String nombre, String apellidos, String direccion) {
        super(login, pass, nombre, apellidos, direccion);
        this.listaDeseos = new ArrayList<>();
    }

    public void agregarAListaDeseos(Inmueble inmueble) {
        this.listaDeseos.add(inmueble);
    }

    public List<Inmueble> getListaDeseos() { return listaDeseos; }
}
