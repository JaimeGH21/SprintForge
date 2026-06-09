package es.uclm.sprintforge.dominio;

import jakarta.persistence.*;

@Entity
public class Inmueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;
    private double precio;
    private String descripcion;
    private boolean directa;

    // Constructor vacío obligatorio para JPA
    public Inmueble() {}

    // ESTE ES EL CONSTRUCTOR QUE USA EL CONTROLADOR
    public Inmueble(String direccion, double precio, String descripcion, boolean directa) {
        this.direccion = direccion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.directa = directa;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public boolean isDirecta() { return directa; }
    public void setDirecta(boolean directa) { this.directa = directa; }
}