package es.uclm.sprintforge.entity;

public class Inmueble {
    private String direccion;
    private double precioNoche;
    private boolean reservaInmediata; // Para saber si permite reserva directa o no

    public Inmueble() {}

    // Getters y Setters
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getPrecioNoche() { return precioNoche; }
    public void setPrecioNoche(double precioNoche) { this.precioNoche = precioNoche; }
}
