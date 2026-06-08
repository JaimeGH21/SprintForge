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

    // EL CONSTRUCTOR VACÍO ES OBLIGATORIO PARA JPA
    public Inmueble() {}

    public Inmueble(String direccion, double precio, String descripcion) {
        this.direccion = direccion;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    // --- ESTOS GETTERS SON OBLIGATORIOS PARA THYMELEAF ---
    public String getDireccion() { return direccion; }
    public double getPrecio() { return precio; }
    
    // ESTE ES EL QUE FALTABA Y PROVOCA EL ERROR 500:
    public String getDescripcion() { return descripcion; }

    // Otros setters necesarios
    public void setDireccion(String d) { this.direccion = d; }
    public void setPrecio(double p) { this.precio = p; }
    public void setDescripcion(String d) { this.descripcion = d; }
    
    public double getPrecioPorNoche() { return this.precio; }
}