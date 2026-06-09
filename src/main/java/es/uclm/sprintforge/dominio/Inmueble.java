package es.uclm.sprintforge.dominio;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Inmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String direccion;
    private double precio;
    private String descripcion;
    
    // NUEVO CAMPO: true = Reserva Inmediata / false = Solicitud de Reserva
    private boolean directa; 

    public Inmueble() {}

    public Inmueble(String direccion, double precio, String descripcion) {
        this.direccion = direccion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.directa = true; // Por defecto inmediata
    }

    public Inmueble(String direccion, double precio, String descripcion, boolean directa) {
        this.direccion = direccion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.directa = directa;
    }

    public Long getId() { return id; }
    public String getDireccion() { return direccion; }
    public double getPrecio() { return precio; }
    public String getDescripcion() { return descripcion; }
    public boolean isDirecta() { return directa; }

    public void setDireccion(String d) { this.direccion = d; }
    public void setPrecio(double p) { this.precio = p; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public void setDirecta(boolean directa) { this.directa = directa; }
    
    public double getPrecioPorNoche() { return this.precio; }
}