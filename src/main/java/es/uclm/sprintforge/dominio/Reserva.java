package es.uclm.sprintforge.dominio;

public class Reserva {
    private String fechaInicio;
    private String fechaFin;
    private boolean pagado;

    public Reserva() {}

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }
    
    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }
    
    public boolean isPagado() { return pagado; }
    public void setPagado(boolean pagado) { this.pagado = pagado; }
}

