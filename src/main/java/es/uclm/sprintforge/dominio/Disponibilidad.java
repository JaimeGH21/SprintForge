package es.uclm.sprintforge.dominio;
import java.util.Date;

public class Disponibilidad {
    private Date fechainicio; 
    private Date fechaFin; 
    private double precio; 
    private boolean directa; 

    public Disponibilidad() {}

    public Date getFechainicio() { return fechainicio; }
    public void setFechainicio(Date fechainicio) { this.fechainicio = fechainicio; }

    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public boolean isDirecta() { return directa; }
    public void setDirecta(boolean directa) { this.directa = directa; }
}
