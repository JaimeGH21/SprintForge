package es.uclm.sprintforge.dominio;

public class Inmueble {
    private String direccion;
    private double precioNoche;
    private PoliticaCancelacion politicaCancelacion;

    public Inmueble() {}

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    
    public double getPrecioNoche() { return precioNoche; }
    public void setPrecioNoche(double precioNoche) { this.precioNoche = precioNoche; }
    
    public PoliticaCancelacion getPoliticaCancelacion() { return politicaCancelacion; }
    public void setPoliticaCancelacion(PoliticaCancelacion politicaCancelacion) { this.politicaCancelacion = politicaCancelacion; }
}


