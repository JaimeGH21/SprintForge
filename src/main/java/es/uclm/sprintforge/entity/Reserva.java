package es.uclm.sprintforge.entity;

import java.util.Date;

public class Reserva {
    private String idReserva;
    private Inmueble inmueble;
    private String nombreCliente;
    private Date fecha;

    public Reserva(String idReserva, Inmueble inmueble, String nombreCliente) {
        this.idReserva = idReserva;
        this.inmueble = inmueble;
        this.nombreCliente = nombreCliente;
        this.fecha = new Date();
    }

    // Getters
    public String getIdReserva() { return idReserva; }
    public Inmueble getInmueble() { return inmueble; }
    public String getNombreCliente() { return nombreCliente; }
    public Date getFecha() { return fecha; }

    @Override
    public String toString() {
        return "Reserva " + idReserva + " | Cliente: " + nombreCliente + " | Inmueble: " + inmueble.getDireccion();
    }
}
