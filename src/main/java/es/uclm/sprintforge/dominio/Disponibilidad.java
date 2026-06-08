package es.uclm.sprintforge.dominio;

import jakarta.persistence.*;
import java.util.Date;
import java.time.temporal.ChronoUnit;
import java.time.ZoneId;

@Entity
public class Disponibilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    private Double precio;
    private boolean directa;
    
    @Enumerated(EnumType.STRING)
    private PoliticaCancelacion politicaCancelacion = PoliticaCancelacion.NO_REEMBOLSABLE;
    
    @ManyToOne
    private Inmueble inmueble;

    public Disponibilidad() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public boolean isDirecta() { return directa; }
    public void setDirecta(boolean directa) { this.directa = directa; }
    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }
    public PoliticaCancelacion getPoliticaCancelacion() { return politicaCancelacion; }
    public void setPoliticaCancelacion(PoliticaCancelacion p) { this.politicaCancelacion = p; }

    public Double getPrecioTotal() {
        if (fechaInicio == null || fechaFin == null || inmueble == null) return null;
        long noches = ChronoUnit.DAYS.between(fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                              fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return inmueble.getPrecioPorNoche() * (noches < 1 ? 1 : noches);
    }
}