package es.uclm.sprintforge.logic;

import es.uclm.sprintforge.entity.Inmueble;
import es.uclm.sprintforge.entity.Reserva;
import java.util.ArrayList;
import java.util.List;

public class GestorReservas {
    private List<Inmueble> inmuebles;
    private List<Reserva> reservas;

    public GestorReservas() {
        this.inmuebles = new ArrayList<>();
        this.reservas = new ArrayList<>();
        // Metemos un par de ejemplos para que no aparezca vacío
        inmuebles.add(new Inmueble("1", "Calle Altagracia 12", 450.0));
        inmuebles.add(new Inmueble("2", "Avenida de los Descubrimientos 4", 600.0));
    }

    public List<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public void añadirReserva(Reserva r) {
        this.reservas.add(r);
        System.out.println("Reserva añadida con éxito: " + r.getIdReserva());
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}
