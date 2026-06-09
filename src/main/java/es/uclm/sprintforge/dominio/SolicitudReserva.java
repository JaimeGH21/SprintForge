package es.uclm.sprintforge.dominio;

import jakarta.persistence.Entity;

@Entity
public class SolicitudReserva extends Reserva {
    private boolean confirmada;

    public SolicitudReserva() {}

    public SolicitudReserva(String descripcion, Usuario usuario, Inmueble inmueble, java.util.Date fechaInicio, java.util.Date fechaFin) {
        super(descripcion, usuario, inmueble, fechaInicio, fechaFin);
        this.confirmada = false;
        this.setActiva(false); // No está activa hasta que se confirme
    }

    public boolean isConfirmada() { return confirmada; }
    public void setConfirmada(boolean confirmada) { this.confirmada = confirmada; }
}