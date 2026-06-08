package es.uclm.sprintforge.dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public class Usuario {
    @Id
    @Column(name = "login")
    private String login;
    private String pass;
    private String nombre;
    private String apellidos;
    private String direccion;

    public Usuario() {}
    public Usuario(String login, String pass, String nombre, String apellidos, String direccion) {
        this.login = login; this.pass = pass; this.nombre = nombre; this.apellidos = apellidos; this.direccion = direccion;
    }
    public String getLogin() { return login; }
    public String getPass() { return pass; }
}