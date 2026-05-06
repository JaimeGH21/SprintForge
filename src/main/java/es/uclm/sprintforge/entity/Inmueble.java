package es.uclm.sprintforge.entity;

public class Inmueble {
    private String id;
    private String direccion;
    private double precio;
    private boolean disponible;

    public Inmueble(String id, String direccion, double precio) {
        this.id = id;
        this.direccion = direccion;
        this.precio = precio;
        this.disponible = true;
    }

    public String getId() { return id; }
    public String getDireccion() { return direccion; }
    public double getPrecio() { return precio; }
    public boolean isDisponible() { return disponible; }
    
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return "ID: " + id + " | Direccion: " + direccion + " | Precio: " + precio + "€";
    }
}
