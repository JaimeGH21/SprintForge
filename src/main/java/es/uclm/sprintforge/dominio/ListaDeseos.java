package es.uclm.sprintforge.dominio;

import jakarta.persistence.*;

@Entity
public class ListaDeseos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Inmueble inmueble;

    public ListaDeseos() {}
    public ListaDeseos(Usuario usuario, Inmueble inmueble) {
        this.usuario = usuario;
        this.inmueble = inmueble;
    }
    // Getters
    public Usuario getUsuario() { return usuario; }
    public Inmueble getInmueble() { return inmueble; }
}