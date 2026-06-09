package es.uclm.sprintforge.dominio;

import jakarta.persistence.Entity;
import java.util.Date;

@Entity
public class SolicitudReserva extends Reserva {
    
    private boolean confirmada;

    public SolicitudReserva() {
        super();
        this.confirmada = false;
    }

    public SolicitudReserva(String descripcion, Usuario usuario, Inmueble inmueble, Date fechaInicio, Date fechaFin) {
        super(descripcion, usuario, inmueble, fechaInicio, fechaFin);
        this.confirmada = false;
    }

    public boolean isConfirmada() { return confirmada; }
    public void setConfirmada(boolean confirmada) { this.confirmada = confirmada; }

    public void confirmarReserva() {
        this.confirmada = true;
        this.setActiva(true); // Se activa al confirmar
    }
}