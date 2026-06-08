package es.uclm.sprintforge.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("INQUILINO")
public class Inquilino extends Usuario {

    public Inquilino() { super(); }

    public Inquilino(String login, String pass, String nombre, String apellidos, String direccion) {
        super(login, pass, nombre, apellidos, direccion);
    }
}