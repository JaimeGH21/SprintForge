package es.uclm.sprintforge.entity;

public class Usuario {
    private String login;
    private String pass;
    private String nombre;
    private String apellidos;
    private String direccion;

    public Usuario() {}

    public String getLogin() { 
        return login; 
    }
    public void setLogin(String login) { 
        this.login = login; 
    }

    public String getPass() { 
        return pass; 
    }
    public void setPass(String pass) { 
        this.pass = pass; 
    }

    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public String getApellidos() { 
        return apellidos; 
    }
    public void setApellidos(String apellidos) { 
        this.apellidos = apellidos; 
    }

    public String getDireccion() { 
        return direccion; 
    }
    public void setDireccion(String direccion) { 
        this.direccion = direccion; 
    }

    public String getAttribute() { 
        return attribute; 
    }
    public void setAttribute(String attribute) { 
        this.attribute = attribute; 
    }
}

