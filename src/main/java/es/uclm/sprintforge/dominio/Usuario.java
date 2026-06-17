package es.uclm.sprintforge.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario") 
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("USUARIO") // <-- EL HACK PARA QUE SQLITE DEJE PASAR EL REGISTRO
public class Usuario {

    @Id
    private String login;
    private String pass;
    private String nombre;
    private String apellidos;
    private String direccion;

    public Usuario() {}

    public Usuario(String login, String pass, String nombre, String apellidos, String direccion) {
        this.login = login;
        this.pass = pass;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}