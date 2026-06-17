package es.uclm.sprintforge.dominio;

import jakarta.persistence.*;

@Entity
public class Notificaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String usuario; // TIENE QUE LLAMARSE IGUAL QUE EN EL HTML
    private String mensaje;

    public Notificaciones() {}

    public Notificaciones(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    // ESTOS MÉTODOS SON OBLIGATORIOS PARA QUE THYMELEAF FUNCIONE
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}